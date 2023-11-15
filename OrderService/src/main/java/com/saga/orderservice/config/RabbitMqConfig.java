package com.saga.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.queue-names.order-paymentFailed}")
    private String orderPaymentFailedQueue;
    @Value("${spring.rabbitmq.queue-names.order-paymentCompleted}")
    private String orderPaymentCompletedQueue;
    @Value("${spring.rabbitmq.queue-names.order.stockNotReserved}")
    private String orderStockNotReservedQueue;


    @Value("${spring.rabbitmq.exchange-names.saga-event-exchange}")
    private String sagaEventExchange;

    @Value("${spring.rabbitmq.routing-keys.order-paymentFailed}")
    private String orderPaymentFailedKey;
    @Value("${spring.rabbitmq.routing-keys.order-paymentCompleted}")
    private String orderPaymentCompletedKey;
    @Value("${spring.rabbitmq.routing-keys.order.stockNotReserved}")
    private String orderStockNotReservedKey;


    @Bean
    public Queue orderPaymentFailedQueue() {
        return new Queue(orderPaymentFailedQueue,true, false, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(sagaEventExchange);
    }

    @Bean
    public Binding orderPaymentFailedQueueBinding() {
        return BindingBuilder.bind(orderPaymentFailedQueue()).to(directExchange()).with(orderPaymentFailedKey);
    }
    @Bean
    public Queue orderPaymentCompletedQueue() {
        return new Queue(orderPaymentCompletedQueue,true, false, false);
    }

    @Bean
    public Binding orderPaymentCompletedQueueBinding() {
        return BindingBuilder.bind(orderPaymentCompletedQueue()).to(directExchange()).with(orderPaymentCompletedKey);
    }
    @Bean
    public Queue orderStockNotReservedQueue() {
        return new Queue(orderStockNotReservedQueue,true, false, false);
    }

    @Bean
    public Binding orderStockNotReservedQueueBinding() {
        return BindingBuilder.bind(orderStockNotReservedQueue()).to(directExchange()).with(orderStockNotReservedKey);
    }
}
