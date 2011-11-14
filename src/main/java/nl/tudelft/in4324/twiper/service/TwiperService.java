package nl.tudelft.in4324.twiper.service;

import nl.tudelft.in4324.twiper.entity.TwiperItem;

import java.util.List;

public interface TwiperService {

    public List<TwiperItem> getDailyTrendingTweets();

}
