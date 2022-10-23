package com.gateway.statistics;

import com.gateway.statistics.json.entity.CurrencyRequestEntity;
import com.gateway.statistics.json.entity.ExchangeRateEntity;
import com.gateway.statistics.json.entity.HistoryExchangeRateEntity;
import com.gateway.statistics.json.entity.XmlCurrencyRequestEntity;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.EnumSet;

@SpringBootApplication
public class StatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticsApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void generateSqlSchema() {
//		MetadataSources metadataSources = new MetadataSources();
//		metadataSources.addAnnotatedClass(ExchangeRateEntity.class);
//		metadataSources.addAnnotatedClass(HistoryExchangeRateEntity.class);
//		metadataSources.addAnnotatedClass(CurrencyRequestEntity.class);
//		metadataSources.addAnnotatedClass(XmlCurrencyRequestEntity.class);
//		Metadata metadata = metadataSources.buildMetadata();
//
//		SchemaExport schemaExport = new SchemaExport();
//		schemaExport.setFormat(true);
//		schemaExport.setOutputFile("create.sql");
//		schemaExport.createOnly(EnumSet.of(TargetType.SCRIPT), metadata);
//	}
}
