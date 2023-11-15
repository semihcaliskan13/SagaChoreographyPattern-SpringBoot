package com.saga.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.queue-names.stock-orderCreated}")
    private String stockOrderCreatedQueue;
}
