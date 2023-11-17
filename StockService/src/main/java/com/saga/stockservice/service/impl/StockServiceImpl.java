package com.saga.stockservice.service.impl;

import com.saga.shared.OrderCreatedEvent;
import com.saga.shared.OrderItemMessage;
import com.saga.shared.PaymentFailedEvent;
import com.saga.stockservice.collection.Stock;
import com.saga.stockservice.repository.StockRepository;
import com.saga.stockservice.service.StockService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository repository;

    public StockServiceImpl(StockRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<Boolean, List<String>> isStockReserved(OrderCreatedEvent event) {
        List<Long> productIds = event.getOrderItems().stream().map(OrderItemMessage::getProductId).toList();
        List<Stock> stocks = repository.findAllByProductIdIn(productIds);
        List<String> errorMessages = new ArrayList<>();
        stocks.forEach(st -> {
            event.getOrderItems().forEach(ot -> {
                if (st.getProductId() == ot.getProductId() && st.getCount() >= ot.getCount()) {
                    st.setCount(st.getCount() - ot.getCount());
                } else {
                    errorMessages.add(String.format("Product with id:%s not available enough stock!", st.getProductId()));
                }
            });
        });
        repository.saveAll(stocks);
        Map<Boolean, List<String>> result = null;
        if (errorMessages.stream().count() != 0) {
            result = new HashMap<>();
            result.put(false, errorMessages);
        }
        return result;
    }

    @Override
    public void compensateStockChanges(PaymentFailedEvent event) {
        List<Long> productIds = event.getOrderItems().stream().map(OrderItemMessage::getProductId).toList();
        List<Stock> stocks = repository.findAllByProductIdIn(productIds);
        stocks.forEach(st -> {
            event.getOrderItems().forEach(ot -> {
                if (st.getProductId() == ot.getProductId() && st.getCount() >= ot.getCount()) {
                    st.setCount(st.getCount() + ot.getCount());
                }
            });
        });
        repository.saveAll(stocks);
    }
}
