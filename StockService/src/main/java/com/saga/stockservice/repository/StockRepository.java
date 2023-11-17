package com.saga.stockservice.repository;

import com.saga.shared.OrderItemMessage;
import com.saga.stockservice.collection.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends MongoRepository<Stock,String> {
    List<Stock> findAllByProductIdIn(List<Long> ids);
}
