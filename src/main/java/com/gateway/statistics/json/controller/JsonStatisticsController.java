package com.gateway.statistics.json.controller;

import com.gateway.statistics.json.model.CurrencyRequestLatest;
import com.gateway.statistics.json.model.CurrencyResponseLatest;
import com.gateway.statistics.json.service.ExchangeRateStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@ResponseBody
@RequestMapping("/json_api")
public class JsonStatisticsController {

    private Logger logger = LoggerFactory.getLogger(JsonStatisticsController.class);

    private ExchangeRateStatisticsService exchangeRateStatisticsService;

    @Autowired
    public JsonStatisticsController(ExchangeRateStatisticsService exchangeRateStatisticsService) {
        this.exchangeRateStatisticsService = exchangeRateStatisticsService;
    }

    @PostMapping(value = "/current", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CurrencyResponseLatest> getCurrentStatistics(HttpServletRequest req, @RequestBody CurrencyRequestLatest exRateRequest) {
        try {
            exchangeRateStatisticsService.validateRequestById(exRateRequest.getRequestId());
            exchangeRateStatisticsService.createExRateRequestRecord(exRateRequest);
            CurrencyResponseLatest responseLatest = exchangeRateStatisticsService.getLatestRatesDataFor(exRateRequest.getCurrency());
            return ResponseEntity.ok(responseLatest);
        } catch (Exception ex) {
            logger.error("API call failed: " + ex.getMessage());
        }
        return ResponseEntity.ok(new CurrencyResponseLatest());
    }

    @PostMapping("/history")
    public void getHistoryStatistics() {

    }
}
