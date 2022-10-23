package com.gateway.statistics.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    private Logger logger = LoggerFactory.getLogger(Sender.class);

    @Value("${statistics.rabbitmq.exchangeName}")
    private String exchange;

    @Value("${statistics.rabbitmq.routingKey}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendRequestMessage(Object message) {
        logger.info("Sending request message to " + exchange);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
