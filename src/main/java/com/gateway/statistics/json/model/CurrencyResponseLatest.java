package com.gateway.statistics.json.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CurrencyResponseLatest {

    private String base;
    private LocalDateTime date;
    private List<Rate> rates = new ArrayList<>();

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
