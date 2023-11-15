package com.saga.orderservice.dto;

import com.saga.orderservice.entity.OrderItem;

import java.util.List;

public record CreateOrderRequest (Long userId, String address, List<OrderItemDto> orderItems){}

