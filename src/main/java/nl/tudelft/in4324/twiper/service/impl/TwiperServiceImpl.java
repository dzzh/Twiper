package nl.tudelft.in4324.twiper.service.impl;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.SearchParameters;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import nl.tudelft.in4324.twiper.commons.connectors.alchemy.AlchemyConnector;
import nl.tudelft.in4324.twiper.commons.connectors.alchemy.entity.AlchemyEntityXml;
import nl.tudelft.in4324.twiper.entity.TwiperItem;
import nl.tudelft.in4324.twiper.entity.TwiperTrend;
import nl.tudelft.in4324.twiper.service.TwiperService;
import nl.tudelft.in4324.twiper.service.TwitterService;
import nl.tudelft.in4324.twiper.util.TwiperException;
import org.springframework.beans.factory.annotation.Required;
import twitter4j.ResponseList;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Tweet;

import java.util.*;

public class TwiperServiceImpl implements TwiperService {

    private static final int TWEETS_FOR_TREND = 3;
    private static final int MAX_TWEETS_TO_CHECK_FOR_TREND = 100;
    private static final int TRENDS_TO_RETRIEVE = 3;
    private static final int MAX_PHOTOS_PER_TWEET = 2;

    private TwitterService twitterService;
    private AlchemyConnector alchemyConnector;
    private Flickr flickr;

    public List<TwiperItem> getDailyTrendingTweets(){
        ResponseList<Trends> trends = twitterService.getDailyTrends();
        Iterator<Trends> trendsIterator = trends.iterator();
        List<TwiperTrend> selectedTrends = new ArrayList<TwiperTrend>();

        //selecting recent twitter trends
        while (selectedTrends.size() < TRENDS_TO_RETRIEVE && trendsIterator.hasNext()){
            Trends currentTrends = trendsIterator.next();
            Iterator<Trend> trendIterator = Arrays.asList(currentTrends.getTrends()).iterator();
            while (selectedTrends.size() < TRENDS_TO_RETRIEVE && trendIterator.hasNext()){
                TwiperTrend twiperTrend = new TwiperTrend(trendIterator.next());
                processTrend(selectedTrends, twiperTrend);
            }
        }
        selectedTrends.size();
        return null;
    }

    private void processTrend(List<TwiperTrend> selectedTrends, TwiperTrend twiperTrend) {
        List<Tweet> tweets = twitterService.getMostPopularTweetsForTrend(twiperTrend, MAX_TWEETS_TO_CHECK_FOR_TREND);
        for (Tweet tweet : tweets){
            if (!tweet.getIsoLanguageCode().equals(Locale.ENGLISH.getLanguage())){
                continue;
            }
            TwiperItem twiperItem = new TwiperItem(tweet);
            twiperItem.setResolvedEntities(alchemyConnector.resolveNamedEntities(twiperItem.getTweet().getText()));
            if (!isItemValid(twiperItem)){
                continue;
            }
            addDbPediaData(twiperItem);
            addFlickrData(twiperItem);
            twiperTrend.addTwiperItem(twiperItem);
            if (twiperTrend.getTwiperItems().size() == TWEETS_FOR_TREND){
                break;
            }
        }
        if (!twiperTrend.getTwiperItems().isEmpty()){
            selectedTrends.add(twiperTrend);
        }
    }

    private void addFlickrData(TwiperItem twiperItem) {
        SearchParameters sp = new SearchParameters();
        sp.setSort(SearchParameters.RELEVANCE);
        List<String> tags = new ArrayList<String>();
        for (AlchemyEntityXml entity : twiperItem.getResolvedEntities().getEntities()){
            tags.add(entity.getText());
        }
        PhotoList photos = getPhotosByTags(tags);

        if (photos.size() < MAX_PHOTOS_PER_TWEET){
            while (tags.size() > 1){
                tags.remove(0);
                PhotoList additionalPhotos = getPhotosByTags(tags);
                if (!additionalPhotos.isEmpty()){
                    while (photos.size() < MAX_PHOTOS_PER_TWEET && !additionalPhotos.isEmpty()){
                        photos.add(additionalPhotos.get(0));
                        additionalPhotos.remove(0);
                    }
                }
            }
        }

        twiperItem.setPhotos(photos);
    }

    private PhotoList getPhotosByTags(List<String> tags) {
        SearchParameters sp = new SearchParameters();
        String[] array = new String[tags.size()];
        sp.setTags(tags.toArray(array));
        PhotoList photos;
        try{
            photos = flickr.getPhotosInterface().search(sp, MAX_PHOTOS_PER_TWEET, 1);
        }   catch (Exception e){
            throw new TwiperException(e);
        }
        return photos;
    }

    private boolean isItemValid(TwiperItem item){
        boolean result = true;
        if (item.getResolvedEntities() == null ||
            item.getResolvedEntities().getEntities().size() == 0){
            result = false;
        }
        return result;
    }

    public void addDbPediaData(TwiperItem twiperItem){
        for (AlchemyEntityXml entity : twiperItem.getResolvedEntities().getEntities()){
            if (entity.getDisambiguation() != null &&
                entity.getDisambiguation().getDbpedia() != null){
                String resource = entity.getDisambiguation().getDbpedia();
                Model jena = ModelFactory.createDefaultModel();
                jena.read(resource);
                entity.setJenaModel(jena);
            }
        }
    }

    @Required
    public void setTwitterService(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @Required
    public void setAlchemyConnector(AlchemyConnector alchemyConnector){
        this.alchemyConnector = alchemyConnector;
    }

    @Required
    public void setFlickr(Flickr flickr){
        this.flickr = flickr;
    }
}
