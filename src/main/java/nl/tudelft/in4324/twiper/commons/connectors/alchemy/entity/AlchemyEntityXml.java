package nl.tudelft.in4324.twiper.commons.connectors.alchemy.entity;

import com.hp.hpl.jena.rdf.model.Literal;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@SuppressWarnings("unused")
@XStreamAlias("entity")
public class AlchemyEntityXml {

    private String type;
    private float relevance;
    private int count;
    private String text;
    @XStreamAlias("disambiguated")
    private AlchemyDisambiguationXml disambiguation;
    @XStreamOmitField
    private Literal label;
    @XStreamOmitField
    private Literal comment;

    public Literal getLabel() {
        return label;
    }

    public void setLabel(Literal label) {
        this.label = label;
    }

    public Literal getComment() {
        return comment;
    }

    public void setComment(Literal comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getRelevance() {
        return relevance;
    }

    public void setRelevance(float relevance) {
        this.relevance = relevance;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AlchemyDisambiguationXml getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(AlchemyDisambiguationXml disambiguation) {
        this.disambiguation = disambiguation;
    }
}
