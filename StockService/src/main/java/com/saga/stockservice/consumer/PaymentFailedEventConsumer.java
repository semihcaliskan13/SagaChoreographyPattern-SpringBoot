package com.saga.stockservice.consumer;

import com.saga.shared.PaymentFailedEvent;
import com.saga.stockservice.service.StockService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;



@Service
public class PaymentFailedEventConsumer {

    private final StockService stockService;

    public PaymentFailedEventConsumer(StockService stockService) {
        this.stockService = stockService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-names.stock-paymentFailed}")
    public void handleMessage(PaymentFailedEvent event) {
       stockService.compensateStockChanges(event);
    }
}
