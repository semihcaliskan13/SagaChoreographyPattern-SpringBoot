package com.saga.orderservice.consumer;

import com.saga.orderservice.entity.Order;
import com.saga.orderservice.enums.OrderStatus;
import com.saga.orderservice.repository.OrderRepository;
import com.saga.shared.StockNotReservedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.yml")
public class StockNotReservedEventConsumer {

    private final OrderRepository repository;

    public StockNotReservedEventConsumer(OrderRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-names.order-stockNotReserved}")
    public void handleMessage(StockNotReservedEvent event) {
        Order order = repository.findById(event.getOrderId()).orElseThrow();
        order.setOrderStatus(OrderStatus.Failed.getValue());
        repository.save(order);
    }
}
