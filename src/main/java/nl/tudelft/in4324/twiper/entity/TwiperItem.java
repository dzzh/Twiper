package nl.tudelft.in4324.twiper.entity;

import twitter4j.Tweet;

public class TwiperItem {

    private Tweet tweet;

    public TwiperItem(Tweet tweet){
        this.tweet = tweet;
    }


    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }
}
