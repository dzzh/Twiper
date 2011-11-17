package nl.tudelft.in4324.twiper.commons.connectors.alchemy.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

@SuppressWarnings("unused")
@XStreamAlias("results")
public class AlchemyNamedEntitiesXml {
    private String status;
    private String usage;
    private String url;
    private String language;
    private List<AlchemyEntityXml> entities;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<AlchemyEntityXml> getEntities() {
        return entities;
    }

    public void setEntities(List<AlchemyEntityXml> entities) {
        this.entities = entities;
    }
}
