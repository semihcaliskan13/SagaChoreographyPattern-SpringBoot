package com.saga.stockservice.service;

import com.saga.shared.OrderCreatedEvent;

import java.util.List;
import java.util.Map;

public interface StockService {

    Map<Boolean, List<String>> isStockReserved(OrderCreatedEvent event);
}
