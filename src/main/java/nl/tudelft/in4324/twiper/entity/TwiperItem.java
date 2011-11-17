package nl.tudelft.in4324.twiper.entity;

import com.aetrion.flickr.photos.PhotoList;
import nl.tudelft.in4324.twiper.commons.connectors.alchemy.entity.AlchemyNamedEntitiesXml;
import twitter4j.Tweet;

public class TwiperItem {

    private Tweet tweet;
    private AlchemyNamedEntitiesXml resolvedEntities;
    private PhotoList photos;

    public TwiperItem(Tweet tweet){
        this.tweet = tweet;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public void setResolvedEntities(AlchemyNamedEntitiesXml resolvedEntities) {
        this.resolvedEntities = resolvedEntities;
    }

    public AlchemyNamedEntitiesXml getResolvedEntities() {
        return resolvedEntities;
    }

    public PhotoList getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoList photos) {
        this.photos = photos;
    }
}
