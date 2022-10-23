package com.gateway.statistics.json.repository;

import com.gateway.statistics.json.entity.XmlCurrencyRequestEntity;
import org.springframework.data.repository.CrudRepository;


public interface XmlCurrencyRequestRepository extends CrudRepository<XmlCurrencyRequestEntity, Long> {

    long countById(String requestId);
}
