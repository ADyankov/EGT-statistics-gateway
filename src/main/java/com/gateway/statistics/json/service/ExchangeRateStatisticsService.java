package com.gateway.statistics.json.service;

import com.gateway.statistics.json.entity.CurrencyRequestEntity;
import com.gateway.statistics.json.entity.ExchangeRateEntity;
import com.gateway.statistics.json.entity.HistoryExchangeRateEntity;
import com.gateway.statistics.json.model.CurrencyResponseHistory;
import com.gateway.statistics.json.model.CurrencyResponseLatest;
import com.gateway.statistics.json.model.JsonCurrencyRequest;
import com.gateway.statistics.json.model.Rate;
import com.gateway.statistics.json.repository.CurrencyRequestRepository;
import com.gateway.statistics.json.repository.ExchangeRateRepository;
import com.gateway.statistics.json.repository.HistoryExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExchangeRateStatisticsService {

    private CurrencyRequestRepository currencyRequestRepository;
    private ExchangeRateRepository exchangeRateRepository;
    private HistoryExchangeRateRepository historyExchangeRateRepository;

    @Autowired
    public ExchangeRateStatisticsService(CurrencyRequestRepository currencyRequestRepository,
                                         ExchangeRateRepository exchangeRateRepository,
                                         HistoryExchangeRateRepository historyExchangeRateRepository) {
        this.currencyRequestRepository = currencyRequestRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.historyExchangeRateRepository = historyExchangeRateRepository;
    }

    public void validateRequestById(UUID requestId) {
        long count = currencyRequestRepository.countById(requestId);
        if (count > 0) {
            throw new IllegalArgumentException("Request with id: " + requestId + " was already executed");
        }
    }

    public void createExRateRequestRecord(JsonCurrencyRequest exRateRequest) {
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
        List<Rate> rates = resp.getRates();
        resultList.forEach(e -> {
            Rate rate = new Rate(e.getQuote(), new BigDecimal(e.getRate()));
            rates.add(rate);
        });
        return resp;
    }

    public CurrencyResponseHistory getHistoryRatesDataFor(String currency, int period) {
        LocalDateTime startingTimeStamp = LocalDateTime.now().minus(period, ChronoUnit.HOURS);
        List<HistoryExchangeRateEntity> resultList = historyExchangeRateRepository.findByBaseAndInterval(currency, startingTimeStamp);
        if (resultList.size() == 0) {
            return new CurrencyResponseHistory();
        }
        CurrencyResponseHistory resp = new CurrencyResponseHistory();
        HistoryExchangeRateEntity exRate = resultList.get(0);
        resp.setBase(exRate.getBase());
        Map<LocalDateTime, List<Rate>> ratesByDate = resultList.stream()
                .collect(Collectors.groupingBy(HistoryExchangeRateEntity::getTimestamp,
                        Collectors.mapping(e -> new Rate(e.getQuote(), new BigDecimal(e.getRate())), Collectors.toList())
                ));
        List<CurrencyResponseHistory.HistoryRate> historyRates = ratesByDate
                .entrySet()
                .stream()
                .map(e -> new CurrencyResponseHistory.HistoryRate(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        resp.setHistory(historyRates);
        return resp;
    }

}
