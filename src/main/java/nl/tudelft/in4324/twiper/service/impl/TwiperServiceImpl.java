package nl.tudelft.in4324.twiper.service.impl;

import com.alchemyapi.api.AlchemyAPI;
import nl.tudelft.in4324.twiper.entity.TwiperItem;
import nl.tudelft.in4324.twiper.entity.TwiperTrend;
import nl.tudelft.in4324.twiper.service.TwiperService;
import nl.tudelft.in4324.twiper.service.TwitterService;
import nl.tudelft.in4324.twiper.util.TwiperException;
import org.springframework.beans.factory.annotation.Required;
import twitter4j.*;

import java.util.*;

public class TwiperServiceImpl implements TwiperService {

    private static final int TWEETS_FOR_TREND = 3;
    private static final int TRENDS_TO_RETRIEVE = 10;

    private TwitterService twitterService;
    private AlchemyAPI alchemyAPI;

    public List<TwiperItem> getDailyTrendingTweets(){
        ResponseList<Trends> trends = twitterService.getDailyTrends();

        Iterator<Trends> trendsIterator = trends.iterator();
        Set<Trend> selectedTrends = new HashSet<Trend>();

        //selecting recent twitter trends
        while (selectedTrends.size() < TRENDS_TO_RETRIEVE && trendsIterator.hasNext()){
            Trends currentTrends = trendsIterator.next();
            Iterator<Trend> trendIterator = Arrays.asList(currentTrends.getTrends()).iterator();
            while (selectedTrends.size() < TRENDS_TO_RETRIEVE && trendIterator.hasNext()){
                selectedTrends.add(trendIterator.next());
            }
        }

        //retrieving popular tweets for all the selected trends
        for (Trend trend : selectedTrends){
            TwiperTrend twiperTrend = new TwiperTrend(trend);
            List<Tweet> tweets = twitterService.getMostPopularTweetsForTrend(trend, TWEETS_FOR_TREND);
            for (Tweet tweet : tweets){
                TwiperItem twiperItem = new TwiperItem(tweet);
                resolveNamedEntities(twiperItem);
                //TODO attach DBpedia values
                //TODO attach Flickr images
                twiperTrend.addTwiperItem(twiperItem);
                //TODO something with the item. Store it in RDF maybe.
            }

        }

        return null;
    }

    //TODO implement
    private boolean isTrendInDatabase(Trend trend){
        return false;
    }

    private void resolveNamedEntities(TwiperItem twiperItem){
        try{
            twiperItem.setResolvedEntities(alchemyAPI.TextGetRankedNamedEntities(twiperItem.getTweet().getText()));
        } catch (Exception e){
            throw new TwiperException(e);
        }
    }

    @Required
    public void setTwitterService(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @Required
    public void setAlchemyAPI(AlchemyAPI alchemyAPI){
        this.alchemyAPI = alchemyAPI;
    }
}
