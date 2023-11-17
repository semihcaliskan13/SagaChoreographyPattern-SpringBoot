package com.saga.stockservice.service.impl;

import com.saga.shared.StockNotReservedEvent;
import com.saga.shared.StockReservedEvent;
import com.saga.stockservice.service.PublishService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublishServiceImpl implements PublishService {


    @Value("${spring.rabbitmq.exchange-names.saga-event-exchange}")
    private String sagaEventExchange;

    @Value("${spring.rabbitmq.routing-keys.payment-stockReserved}")
    private String paymentStockReservedKey;

    @Value("${spring.rabbitmq.routing-keys.order-stockNotReserved}")
    private String orderStockNotReservedKey;
    private final RabbitTemplate rabbitTemplate;

    public PublishServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void stockReservedEventPublish(StockReservedEvent event) {
        rabbitTemplate.convertAndSend(sagaEventExchange,paymentStockReservedKey,event);
    }

    @Override
    public void stockNotReservedEventPublish(StockNotReservedEvent event) {
        rabbitTemplate.convertAndSend(sagaEventExchange,orderStockNotReservedKey,event);
    }
}
