package com.gateway.statistics.json.controller;

import com.gateway.statistics.json.model.CurrencyResponseHistory;
import com.gateway.statistics.json.model.CurrencyResponseLatest;
import com.gateway.statistics.json.model.JsonCurrencyRequest;
import com.gateway.statistics.rabbitmq.Sender;
import com.gateway.statistics.service.ExchangeRateStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/json_api")
public class JsonStatisticsController {

    private Logger logger = LoggerFactory.getLogger(JsonStatisticsController.class);

    private ExchangeRateStatisticsService exchangeRateStatisticsService;
    private Sender rabbitmqSender;

    @Autowired
    public JsonStatisticsController(ExchangeRateStatisticsService exchangeRateStatisticsService,
                                    Sender rabbitmqSender) {
        this.exchangeRateStatisticsService = exchangeRateStatisticsService;
        this.rabbitmqSender = rabbitmqSender;
    }

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyResponseLatest> getLatestStatistics(@RequestBody JsonCurrencyRequest exRateRequest) {
        try {
            exchangeRateStatisticsService.validateRequestById(exRateRequest.getRequestId());
            exchangeRateStatisticsService.createExRateRequestRecord(exRateRequest);
            rabbitmqSender.sendRequestMessage(exRateRequest);
            CurrencyResponseLatest responseLatest = exchangeRateStatisticsService.getLatestRatesDataFor(exRateRequest.getCurrency());
            return ResponseEntity.ok(responseLatest);
        } catch (Exception ex) {
            logger.error("API call failed: " + ex.getMessage());
            throw ex;
        }
    }

    @PostMapping(value = "/history", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyResponseHistory> getHistoryStatistics(@RequestBody JsonCurrencyRequest exRateRequest) {
        try {
            exchangeRateStatisticsService.validateRequestById(exRateRequest.getRequestId());
            exchangeRateStatisticsService.createExRateRequestRecord(exRateRequest);
            rabbitmqSender.sendRequestMessage(exRateRequest);
            CurrencyResponseHistory responseHistory = exchangeRateStatisticsService.getHistoryRatesDataFor(exRateRequest.getCurrency(), exRateRequest.getPeriod());
            return ResponseEntity.ok(responseHistory);
        } catch (Exception ex) {
            logger.error("API call failed: " + ex.getMessage());
            throw ex;
        }
    }
}
