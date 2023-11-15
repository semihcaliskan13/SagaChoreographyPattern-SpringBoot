package com.saga.orderservice.consumer;

import com.saga.shared.PaymentFailedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.yml")
public class PaymentFailedEventConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue-names.order-paymentFailed}")
    public void handleMessage(PaymentFailedEvent event){

    }

}
