package com.gateway.statistics.json.repository;

import com.gateway.statistics.json.entity.HistoryExchangeRateEntity;
import com.gateway.statistics.json.entity.HistoryExchangeRateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryExchangeRateRepository extends CrudRepository<HistoryExchangeRateEntity, HistoryExchangeRateId> {

    @Query("FROM HistoryExchangeRateEntity hist WHERE hist.base=:base AND hist.timestamp >=:timestamp")
    List<HistoryExchangeRateEntity> findByBaseAndInterval(@Param("base") String base, @Param("timestamp")LocalDateTime timestamp);

}
