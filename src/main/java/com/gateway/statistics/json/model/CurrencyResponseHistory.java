package com.gateway.statistics.json.model;

import java.time.LocalDateTime;
import java.util.List;

public class CurrencyResponseHistory {

    private String base;
    private List<HistoryRate> history;


    public static class HistoryRate {
        private LocalDateTime date;
        private List<Rate> rates;

        public HistoryRate(LocalDateTime date, List<Rate> rates) {
            this.date = date;
            this.rates = rates;
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

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public List<HistoryRate> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryRate> history) {
        this.history = history;
    }
}
