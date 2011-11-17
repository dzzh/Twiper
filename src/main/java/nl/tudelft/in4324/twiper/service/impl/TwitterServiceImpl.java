package nl.tudelft.in4324.twiper.service.impl;

import nl.tudelft.in4324.twiper.entity.TwiperTrend;
import nl.tudelft.in4324.twiper.service.TwitterService;
import nl.tudelft.in4324.twiper.util.TwiperException;
import twitter4j.*;

import java.util.List;
import java.util.Locale;

public class TwitterServiceImpl implements TwitterService {

    private Twitter twitter;

    public TwitterServiceImpl(){
        this.twitter = new TwitterFactory().getInstance();
    }

    public List<Tweet> getMostPopularTweetsForTrend(TwiperTrend trend, int numberTweets) {
        Query query = new Query(trend.getTrend().getQuery());
        query.setResultType(Query.MIXED);
        query.setRpp(numberTweets);
        query.setPage(1);
        query.setLang(Locale.ENGLISH.getLanguage());

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
