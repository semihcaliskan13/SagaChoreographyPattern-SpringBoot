package com.saga.orderservice.api.Controller;

import com.saga.orderservice.dto.CreateOrderRequest;
import com.saga.orderservice.entity.Order;
import com.saga.orderservice.entity.OrderItem;
import com.saga.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public BigDecimal createOrder(@RequestBody CreateOrderRequest request) {
        Order order = new Order();
        order.setAddress(request.address());
        order.setUserId(request.userId());
        order.setOrderItems( request.orderItems().stream().map(x->{
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(x.getCount());
            orderItem.setPrice(x.getPrice());
            orderItem.setProductId(x.getProductId());
            return orderItem;
        }).collect(Collectors.toList()));
        BigDecimal totalPrice = request.orderItems().stream()
                .map(orderItem -> orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        return totalPrice;
    }



}
