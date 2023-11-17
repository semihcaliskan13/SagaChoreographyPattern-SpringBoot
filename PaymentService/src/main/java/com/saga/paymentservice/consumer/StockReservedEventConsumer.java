package com.saga.paymentservice.consumer;

import com.saga.shared.PaymentCompletedEvent;
import com.saga.shared.PaymentFailedEvent;
import com.saga.shared.StockReservedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class StockReservedEventConsumer {

    @Value("${spring.rabbitmq.exchange-names.saga-event-exchange}")
    private String sagaEventExchange;

    @Value("${spring.rabbitmq.routing-keys.order-paymentCompleted}")
    private String orderPaymentCompletedKey;

    @Value("${spring.rabbitmq.routing-keys.order-paymentFailed}")
    private String orderPaymentFailedKey;

    @Value("${spring.rabbitmq.routing-keys.stock-paymentFailed}")
    private String stockPaymentFailedKey;

    private final RabbitTemplate rabbitTemplate;

    public StockReservedEventConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-names.payment-stockReserved}")
    public void handleMessage(StockReservedEvent event) {

        if (event.getTotalPrice().compareTo(BigDecimal.valueOf(100L)) < 0) {
            PaymentCompletedEvent paymentCompletedEvent = new PaymentCompletedEvent(event.getOrderId());
            rabbitTemplate.convertAndSend(sagaEventExchange, orderPaymentCompletedKey, paymentCompletedEvent);
        } else {
            PaymentFailedEvent paymentFailedEvent = new PaymentFailedEvent();
            BeanUtils.copyProperties(event, paymentFailedEvent);

            rabbitTemplate.convertAndSend(sagaEventExchange, orderPaymentFailedKey, paymentFailedEvent);
            rabbitTemplate.convertAndSend(sagaEventExchange,stockPaymentFailedKey,paymentFailedEvent);
        }
    }
}
