package nl.tudelft.in4324.twiper.commons.connectors.alchemy;

import com.alchemyapi.api.AlchemyAPI;
import com.thoughtworks.xstream.XStream;
import nl.tudelft.in4324.twiper.commons.connectors.alchemy.entity.AlchemyDisambiguationXml;
import nl.tudelft.in4324.twiper.commons.connectors.alchemy.entity.AlchemyEntityXml;
import nl.tudelft.in4324.twiper.commons.connectors.alchemy.entity.AlchemyNamedEntitiesXml;
import nl.tudelft.in4324.twiper.util.TwiperException;
import org.springframework.beans.factory.annotation.Required;
import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class AlchemyConnectorImpl implements AlchemyConnector {

    private AlchemyAPI alchemyAPI;
    private XStream xstream;

    public AlchemyConnectorImpl(){
        xstream = new XStream();

        Class[] annotatedClasses = new Class[4];
        annotatedClasses[0] = AlchemyDisambiguationXml.class;
        annotatedClasses[1] = AlchemyEntityXml.class;
        annotatedClasses[2] = AlchemyNamedEntitiesXml.class;

        xstream.processAnnotations(annotatedClasses);
    }

    public AlchemyNamedEntitiesXml resolveNamedEntities(String text){
        try{
            Document entities = alchemyAPI.TextGetRankedNamedEntities(text);
            String xml = getStringFromDocument(entities);
            return (AlchemyNamedEntitiesXml)xstream.fromXML(xml);
        } catch (Exception e){
            throw new TwiperException(e);
        }
    }

    private static String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            return writer.toString();
        } catch (TransformerException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Required
    public void setAlchemyAPI(AlchemyAPI alchemyAPI) {
        this.alchemyAPI = alchemyAPI;
    }

}
