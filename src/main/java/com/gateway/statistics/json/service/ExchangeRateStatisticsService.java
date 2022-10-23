package com.gateway.statistics.json.service;

import com.gateway.statistics.json.entity.CurrencyRequestEntity;
import com.gateway.statistics.json.entity.ExchangeRateEntity;
import com.gateway.statistics.json.model.CurrencyRequestLatest;
import com.gateway.statistics.json.model.CurrencyResponseLatest;
import com.gateway.statistics.json.repository.CurrencyRequestRepository;
import com.gateway.statistics.json.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ExchangeRateStatisticsService {

    private CurrencyRequestRepository currencyRequestRepository;
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateStatisticsService(CurrencyRequestRepository currencyRequestRepository, ExchangeRateRepository exchangeRateRepository) {
        this.currencyRequestRepository = currencyRequestRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public void validateRequestById(UUID requestId) {
        long count = currencyRequestRepository.countById(requestId);
        if (count > 0) {
            throw new IllegalArgumentException("Request with id: " + requestId + " was already executed");
        }
    }

    public void createExRateRequestRecord(CurrencyRequestLatest exRateRequest) {
        CurrencyRequestEntity exRate = new CurrencyRequestEntity(exRateRequest);
        currencyRequestRepository.save(exRate);
    }

    public CurrencyResponseLatest getLatestRatesDataFor(String currency) {
        List<ExchangeRateEntity> resultList = exchangeRateRepository.findByBase(currency);
        if (resultList.size() == 0) {
            return new CurrencyResponseLatest();
        }
        ExchangeRateEntity exRate = resultList.get(0);
        CurrencyResponseLatest resp = new CurrencyResponseLatest();
        resp.setBase(exRate.getBase());
        resp.setDate(exRate.getTimestamp());
        List<CurrencyResponseLatest.Rate> rates = resp.getRates();
        resultList.forEach(e -> {
            CurrencyResponseLatest.Rate rate = new CurrencyResponseLatest.Rate(e.getQuote(), new BigDecimal(e.getRate()));
            rates.add(rate);
        });
        return resp;
    }

}