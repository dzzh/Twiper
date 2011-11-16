package nl.tudelft.in4324.twiper.entity;

import org.w3c.dom.Document;
import twitter4j.Tweet;

public class TwiperItem {

    private Tweet tweet;
    private Document resolvedEntities;

    public TwiperItem(Tweet tweet){
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public Document getResolvedEntities() {
        return resolvedEntities;
    }

    public void setResolvedEntities(Document resolvedEntities) {
        this.resolvedEntities = resolvedEntities;
    }
}
