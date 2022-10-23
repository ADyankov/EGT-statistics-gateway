package com.gateway.statistics.json.repository;

import com.gateway.statistics.json.entity.CurrencyRequestEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CurrencyRequestRepository extends CrudRepository<CurrencyRequestEntity, UUID> {

    long countById(UUID id);
}
