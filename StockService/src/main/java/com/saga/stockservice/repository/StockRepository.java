package com.saga.stockservice.repository;

import com.saga.stockservice.collection.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock,String> {
}
