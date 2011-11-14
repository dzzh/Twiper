package nl.tudelft.in4324.twiper.service.impl;

import nl.tudelft.in4324.twiper.entity.TwiperItem;
import nl.tudelft.in4324.twiper.service.TwiperService;
import nl.tudelft.in4324.twiper.service.TwitterService;
import org.springframework.beans.factory.annotation.Required;
import twitter4j.*;

import java.util.*;

public class TwiperServiceImpl implements TwiperService {

    private static final int TWEETS_FOR_TREND = 3;
    private static final int TRENDS_TO_RETRIEVE = 10;

    private TwitterService twitterService;

    public List<TwiperItem> getDailyTrendingTweets(){
        ResponseList<Trends> trends = twitterService.getDailyTrends();

        Iterator<Trends> trendsIterator = trends.iterator();
        Set<String> selectedTrends = new HashSet<String>();

        //selecting recent twitter trends
        while (selectedTrends.size() < TRENDS_TO_RETRIEVE && trendsIterator.hasNext()){
            Trends currentTrends = trendsIterator.next();
            Iterator<Trend> trendIterator = Arrays.asList(currentTrends.getTrends()).iterator();
            while (selectedTrends.size() < TRENDS_TO_RETRIEVE && trendIterator.hasNext()){
                Trend currentTrend = trendIterator.next();
                selectedTrends.add(currentTrend.getQuery());
            }
        }

        //retrieving popular tweets for all the selected trends
        for (String trend : selectedTrends){


        }

        return null;
    }

    //TODO implement
    private boolean isTrendInDatabase(Trend trend){
        return false;
    }

    @Required
    public void setTwitterService(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

}
