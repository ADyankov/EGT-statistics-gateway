package com.gateway.statistics.json.entity;

import com.gateway.statistics.xml.model.LatestRequestXml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "xml_currency_request")
public class XmlCurrencyRequestEntity {

    public XmlCurrencyRequestEntity(LatestRequestXml latestRequestXml) {
        this.id = latestRequestXml.getId();
        this.clientId = latestRequestXml.getGet().getConsumer();
        this.currency = latestRequestXml.getGet().getCurrency();
    }

    public XmlCurrencyRequestEntity() {
    }

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "clientId")
    private String clientId;

    @Column(name = "currency")
    private String currency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
