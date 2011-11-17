package nl.tudelft.in4324.twiper.web;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

public abstract class AbstractXmlView implements View {

    public static final String XML_CONTENT_TYPE = "text/xml; charset=UTF-8";

    public String getContentType() {
        return XML_CONTENT_TYPE;
    }

    @SuppressWarnings("unchecked")
    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        Writer responseWriter = new BufferedWriter(
                new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
        responseWriter.write(generateXml());
        responseWriter.flush();
        response.flushBuffer();
    }

    /**
     * @return the XML that is to be sent
     */
    protected abstract String generateXml();
}