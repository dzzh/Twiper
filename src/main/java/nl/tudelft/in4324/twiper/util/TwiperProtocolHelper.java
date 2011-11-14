package nl.tudelft.in4324.twiper.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class TwiperProtocolHelper {

    public static Map<String, String> parseHeaderParameters(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<String, String>();
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement().toUpperCase();
            headers.put(name, request.getHeader(name));
        }

        return headers;
    }
}
