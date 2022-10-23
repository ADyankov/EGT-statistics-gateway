package com.gateway.statistics.xml.model;

import javax.xml.bind.annotation.XmlAttribute;

public class History {

    private String consumer;
    private String currency;
    private String period;

    @XmlAttribute
    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    @XmlAttribute
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlAttribute
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
