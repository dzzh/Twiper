package nl.tudelft.in4324.twiper.service;

import twitter4j.ResponseList;
import twitter4j.Trends;
import twitter4j.Tweet;

import java.util.List;

public interface TwitterService {

    public List<Tweet> getMostPopularTweetsForTrend(String trend, int number);
    public ResponseList<Trends> getDailyTrends();

}
