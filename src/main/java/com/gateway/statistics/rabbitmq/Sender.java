package com.gateway.statistics.rabbitmq;

import com.gateway.statistics.json.model.JsonCurrencyRequest;
import com.gateway.statistics.xml.model.LatestRequestXml;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Value("${statistics.rabbitmq.exchangeName}")
    private String exchange;

    @Value("${statistics.rabbitmq.routingKey}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonRequestMessage(JsonCurrencyRequest message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    public void sendXmlRequestMessage(LatestRequestXml message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
