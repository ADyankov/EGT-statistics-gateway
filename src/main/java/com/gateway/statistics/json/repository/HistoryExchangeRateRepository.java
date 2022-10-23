package com.gateway.statistics.json.repository;

import com.gateway.statistics.json.entity.HistoryExchangeRateEntity;
import com.gateway.statistics.json.entity.HistoryExchangeRateId;
import org.springframework.data.repository.CrudRepository;

public interface HistoryExchangeRateRepository extends CrudRepository<HistoryExchangeRateEntity, HistoryExchangeRateId> {
}
