package com.gateway.statistics.json.repository;

import com.gateway.statistics.json.entity.ExchangeRateEntity;
import com.gateway.statistics.json.entity.ExchangeRateId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRateEntity, ExchangeRateId> {

    List<ExchangeRateEntity> findByBase(String base);
}
