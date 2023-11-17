package com.saga.orderservice.consumer;

import com.saga.orderservice.entity.Order;
import com.saga.orderservice.enums.OrderStatus;
import com.saga.orderservice.repository.OrderRepository;
import com.saga.shared.PaymentCompletedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.yml")
public class PaymentCompletedEventConsumer {
    private final OrderRepository orderRepository;

    public PaymentCompletedEventConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-names.order-paymentCompleted}")
    public void handleMessage(PaymentCompletedEvent event){
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow();
        order.setOrderStatus(OrderStatus.Completed.name());
        orderRepository.save(order);
    }
}
