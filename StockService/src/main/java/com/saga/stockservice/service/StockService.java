package com.saga.stockservice.service;

import com.saga.shared.OrderCreatedEvent;
import com.saga.shared.PaymentFailedEvent;

import java.util.List;
import java.util.Map;

public interface StockService {

    Map<Boolean, List<String>> isStockReserved(OrderCreatedEvent event);

    void compensateStockChanges(PaymentFailedEvent event);
}
