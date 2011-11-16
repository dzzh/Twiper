package nl.tudelft.in4324.twiper.service.impl;

import nl.tudelft.in4324.twiper.service.TwitterService;
import nl.tudelft.in4324.twiper.util.TwiperException;
import twitter4j.*;

import java.util.List;

public class TwitterServiceImpl implements TwitterService {

    private Twitter twitter;

    public TwitterServiceImpl(){
        this.twitter = new TwitterFactory().getInstance();
    }

    public List<Tweet> getMostPopularTweetsForTrend(Trend trend, int numberTweets) {
        Query query = new Query(trend.getQuery());
        query.setResultType(Query.POPULAR);
        query.setRpp(numberTweets);
        query.setPage(1);

        try{
            return twitter.search(query).getTweets();
        } catch (TwitterException twe){
            throw new TwiperException(twe);
        }
    }

    public ResponseList<Trends> getDailyTrends() {
        ResponseList<Trends> result;

        try{
            result = twitter.getDailyTrends();
        } catch(TwitterException twe){
            throw new TwiperException(twe);
        }

        return result;
    }


}
