package com.saga.orderservice.api.Controller;

import com.saga.orderservice.dto.CreateOrderRequest;
import com.saga.orderservice.entity.Order;
import com.saga.orderservice.repository.OrderRepository;
import com.saga.orderservice.service.PublisherService;
import com.saga.shared.OrderCreatedEvent;
import com.saga.shared.OrderItemMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.saga.orderservice.mapper.OrderMapper.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final PublisherService publisherService;

    public OrderController(OrderRepository orderRepository, PublisherService publisherService) {
        this.orderRepository = orderRepository;
        this.publisherService = publisherService;
    }

    @PostMapping
    public void createOrder(@RequestBody CreateOrderRequest request) {
        Order order = map.createOrderRequestToOrder(request);
        order =orderRepository.save(order);
        List<OrderItemMessage> orderItemMessages = map.orderItemToOrderItemMessage(order.getOrderItems());
        publisherService.sendOrderCreatedEvent(new OrderCreatedEvent(order.getId(),order.getUserId(),order.getTotalPrice(),orderItemMessages));
    }
}
