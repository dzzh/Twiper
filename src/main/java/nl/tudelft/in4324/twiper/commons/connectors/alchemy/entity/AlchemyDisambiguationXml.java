package nl.tudelft.in4324.twiper.commons.connectors.alchemy.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@XStreamAlias("disambiguated")
public class AlchemyDisambiguationXml {

    @XStreamImplicit(itemFieldName = "subType")
    private List<String> subtypes;

    private String name;
    private String website;
    private String geo;
    private String dbpedia;
    private String yago;
    private String opencyc;
    private String umbel;
    private String freebase;
    private String ciaFactbook;
    private String census;
    private String geonames;
    private String musicBrainz;
    private String crunchbase;
    private String semanticCrunchbase;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getYago() {
        return yago;
    }

    public void setYago(String yago) {
        this.yago = yago;
    }

    public String getOpencyc() {
        return opencyc;
    }

    public void setOpencyc(String opencyc) {
        this.opencyc = opencyc;
    }

    public String getUmbel() {
        return umbel;
    }

    public void setUmbel(String umbel) {
        this.umbel = umbel;
    }

    public String getFreebase() {
        return freebase;
    }

    public void setFreebase(String freebase) {
        this.freebase = freebase;
    }

    public String getCiaFactbook() {
        return ciaFactbook;
    }

    public void setCiaFactbook(String ciaFactbook) {
        this.ciaFactbook = ciaFactbook;
    }

    public String getCensus() {
        return census;
    }

    public void setCensus(String census) {
        this.census = census;
    }

    public String getGeonames() {
        return geonames;
    }

    public void setGeonames(String geonames) {
        this.geonames = geonames;
    }

    public String getMusicBrainz() {
        return musicBrainz;
    }

    public void setMusicBrainz(String musicBrainz) {
        this.musicBrainz = musicBrainz;
    }

    public String getCrunchbase() {
        return crunchbase;
    }

    public void setCrunchbase(String crunchbase) {
        this.crunchbase = crunchbase;
    }

    public String getSemanticCrunchbase() {
        return semanticCrunchbase;
    }

    public void setSemanticCrunchbase(String semanticCrunchbase) {
        this.semanticCrunchbase = semanticCrunchbase;
    }

    public AlchemyDisambiguationXml(){
        this.subtypes = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbpedia() {
        return dbpedia;
    }

    public void setDbpedia(String dbpedia) {
        this.dbpedia = dbpedia;
    }

    public List<String> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(List<String> subtypes) {
        this.subtypes = subtypes;
    }
}
