package com.saga.stockservice.service;

import com.saga.shared.StockNotReservedEvent;
import com.saga.shared.StockReservedEvent;

public interface PublishService {

    void stockReservedEventPublish(StockReservedEvent event);
    void stockNotReservedEventPublish(StockNotReservedEvent event);
}
