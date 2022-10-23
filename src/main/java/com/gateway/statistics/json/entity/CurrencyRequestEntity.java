package com.gateway.statistics.json.entity;

import com.gateway.statistics.json.model.JsonCurrencyRequest;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "currency_request")
public class CurrencyRequestEntity {

    public CurrencyRequestEntity() {}

    public CurrencyRequestEntity(JsonCurrencyRequest exRate) {
        this.id = exRate.getRequestId();
        this.serviceName = exRate.getServiceName();
        this.timestamp = exRate.getTimestamp();
        this.client = exRate.getClient();
        this.currency = exRate.getCurrency();
    }

    @Id
    @Column(name = "id")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "service")
    private String serviceName;

    @Column(name = "timestamp")
    private Long timestamp;

    @Column(name = "client")
    private int client;

    @Column(name = "currency")
    private String currency;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
