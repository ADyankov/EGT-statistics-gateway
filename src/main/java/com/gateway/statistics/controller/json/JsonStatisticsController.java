package com.gateway.statistics.controller.json;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonStatisticsController {

    @PostMapping("/json_api/current")
    public void getCurrentStatistics() {

    }

    @PostMapping("/json_api/history")
    public void getHistoryStatistics() {

    }
}
