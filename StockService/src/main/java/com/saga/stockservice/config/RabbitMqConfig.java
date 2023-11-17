package com.saga.stockservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.queue-names.stock-orderCreated}")
    private String stockOrderCreatedQueue;

    @Value("${spring.rabbitmq.queue-names.payment-stockReserved}")
    private String paymentStockReservedQueue;

    @Value("${spring.rabbitmq.queue-names.order-stockNotReserved}")
    private String orderStockNotReservedQueue;

    @Value("${spring.rabbitmq.exchange-names.saga-event-exchange}")
    private String sagaEventExchange;

    @Value("${spring.rabbitmq.routing-keys.stock-orderCreated}")
    private String stockOrderCreatedKey;

    @Value("${spring.rabbitmq.routing-keys.payment-stockReserved}")
    private String paymentStockReservedKey;

    @Value("${spring.rabbitmq.routing-keys.order-stockNotReserved}")
    private String orderStockNotReservedKey;



    @Bean
    public Queue stockOrderCreatedQueue() {
        return new Queue(stockOrderCreatedQueue,true, false, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(sagaEventExchange);
    }

    @Bean
    public Binding stockOrderCreatedQueueBinding() {
        return BindingBuilder.bind(stockOrderCreatedQueue()).to(directExchange()).with(stockOrderCreatedKey);
    }


    @Bean
    public Queue paymentStockReservedQueue() {
        return new Queue(paymentStockReservedQueue,true, false, false);
    }


    @Bean
    public Binding paymentStockReservedQueueBinding() {
        return BindingBuilder.bind(paymentStockReservedQueue()).to(directExchange()).with(paymentStockReservedKey);
    }

    @Bean
    public Queue orderStockNotReservedQueue() {
        return new Queue(orderStockNotReservedQueue,true, false, false);
    }


    @Bean
    public Binding orderStockNotReservedQueueBinding() {
        return BindingBuilder.bind(orderStockNotReservedQueue()).to(directExchange()).with(orderStockNotReservedKey);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
