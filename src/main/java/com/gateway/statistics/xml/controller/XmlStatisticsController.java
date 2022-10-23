package com.gateway.statistics.xml.controller;

import com.gateway.statistics.json.model.CurrencyResponseLatest;
import com.gateway.statistics.rabbitmq.Sender;
import com.gateway.statistics.service.ExchangeRateStatisticsService;
import com.gateway.statistics.xml.model.HistoryRequestXml;
import com.gateway.statistics.xml.model.LatestRequestXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XmlStatisticsController {

    private Logger logger = LoggerFactory.getLogger(XmlStatisticsController.class);

    private ExchangeRateStatisticsService exchangeRateStatisticsService;
    private Sender rabbitmqSender;

    public XmlStatisticsController(ExchangeRateStatisticsService exchangeRateStatisticsService, Sender rabbitmqSender) {
        this.exchangeRateStatisticsService = exchangeRateStatisticsService;
        this.rabbitmqSender = rabbitmqSender;
    }

    @RequestMapping(value = "/xml_api/command", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CurrencyResponseLatest> getLatestStatistics(@RequestBody LatestRequestXml request) {
        try {
            exchangeRateStatisticsService.validateXmlRequestById(request.getId());
            exchangeRateStatisticsService.createXmlExRateRequestRecord(request);
            rabbitmqSender.sendRequestMessage(request);
            CurrencyResponseLatest responseLatest = exchangeRateStatisticsService.getLatestRatesDataFor(request.getGet().getCurrency());
            return ResponseEntity.ok(responseLatest);
        } catch (Exception ex) {
            logger.error("API call failed: " + ex.getMessage());
            throw ex;
        }
    }

    @RequestMapping(value = "/xml_api/history", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CurrencyResponseLatest> getHistoryStatistics(@RequestBody HistoryRequestXml request) {
        try {
            exchangeRateStatisticsService.validateXmlRequestById(request.getId());
            rabbitmqSender.sendRequestMessage(request);
            CurrencyResponseLatest responseLatest = exchangeRateStatisticsService.getLatestRatesDataFor(request.getHistory().getCurrency());
            return ResponseEntity.ok(responseLatest);
        } catch (Exception ex) {
            logger.error("API call failed: " + ex.getMessage());
            throw ex;
        }
    }
}
