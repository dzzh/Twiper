package nl.tudelft.in4324.twiper.entity;

import twitter4j.Trend;

import java.util.ArrayList;
import java.util.List;

public class TwiperTrend {

    private List<TwiperItem> twiperItems;
    private Trend trend;

    public TwiperTrend(Trend trend){
        this.twiperItems = new ArrayList<TwiperItem>();
        this.trend = trend;
    }

    public void setTwiperItems(List<TwiperItem> twiperItems){
        this.twiperItems = twiperItems;
    }

    public void addTwiperItem(TwiperItem twiperItem){
        this.twiperItems.add(twiperItem);
    }

    public List<TwiperItem> getTwiperItems(){
        return this.twiperItems;
    }

    public Trend getTrend(){
        return this.trend;
    }

}
