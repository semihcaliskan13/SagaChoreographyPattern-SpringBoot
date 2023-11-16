package com.saga.stockservice.runner;

import com.saga.stockservice.collection.Stock;
import com.saga.stockservice.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final StockRepository stockRepository;

    public DatabaseInit(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stock stock = new Stock();
        stock.setProductId(5L);
        stock.setCount(10L);
        stockRepository.save(stock);
    }
}
