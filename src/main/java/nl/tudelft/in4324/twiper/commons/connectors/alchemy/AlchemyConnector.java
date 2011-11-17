package nl.tudelft.in4324.twiper.commons.connectors.alchemy;

import nl.tudelft.in4324.twiper.commons.connectors.alchemy.entity.AlchemyNamedEntitiesXml;

public interface AlchemyConnector {

    public AlchemyNamedEntitiesXml resolveNamedEntities(String text);
}
