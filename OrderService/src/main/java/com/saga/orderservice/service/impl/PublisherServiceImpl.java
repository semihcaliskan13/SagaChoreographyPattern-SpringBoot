package com.saga.orderservice.service.impl;

import com.saga.orderservice.service.PublisherService;
import com.saga.shared.OrderCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.yml")
public class PublisherServiceImpl implements PublisherService {

    @Value("${spring.rabbitmq.exchange-names.saga-event-exchange}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing-keys.stock-orderCreated}")
    private String stockOrderCreatedKey;

    private final RabbitTemplate rabbitTemplate;

    public PublisherServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        rabbitTemplate.convertAndSend(exchangeName,stockOrderCreatedKey,event);
    }
}
