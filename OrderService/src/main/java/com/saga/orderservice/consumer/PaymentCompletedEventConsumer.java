package com.saga.orderservice.consumer;

import com.saga.shared.PaymentCompletedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.yml")
public class PaymentCompletedEventConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue-names.order-paymentCompleted}")
    public void handleMessage(PaymentCompletedEvent event){

    }
}
