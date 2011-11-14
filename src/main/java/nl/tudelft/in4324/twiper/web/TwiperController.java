package nl.tudelft.in4324.twiper.web;

import nl.tudelft.in4324.twiper.entity.TwiperItem;
import nl.tudelft.in4324.twiper.service.TwiperService;
import nl.tudelft.in4324.twiper.util.TwiperProtocolHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import twitter4j.ResponseList;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class TwiperController extends MultiActionController{

    private static final Logger LOG = LoggerFactory.getLogger(TwiperController.class);

    private TwiperService twiperService;

    public ModelAndView getDailyTrends(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String,String> headers = TwiperProtocolHelper.parseHeaderParameters(request);
        List<TwiperItem> trendingItems = twiperService.getDailyTrendingTweets();
        return new ModelAndView(new RdfView());
    }

    @Required
    public void setTwiperService(TwiperService twiperService) {
        this.twiperService = twiperService;
    }

}
