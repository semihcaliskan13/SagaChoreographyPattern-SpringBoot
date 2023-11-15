package com.saga.orderservice.consumer;

import com.saga.shared.StockReservedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.yml")
public class StockNotReservedEventConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue-names.order-stockNotReserved}")
    public void handleMessage(StockReservedEvent event){

    }
}
