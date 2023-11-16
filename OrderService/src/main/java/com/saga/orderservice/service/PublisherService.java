package com.saga.orderservice.service;

import com.saga.shared.OrderCreatedEvent;

public interface PublisherService {

    void sendOrderCreatedEvent(OrderCreatedEvent event);
}
