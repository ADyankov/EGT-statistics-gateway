package com.gateway.statistics.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "command")
public class LatestRequestXml {

    private Get get;
    private String id;

    @XmlElement
    public Get getGet() {
        return get;
    }
    public void setGet(Get get) {
        this.get = get;
    }
    @XmlAttribute
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}