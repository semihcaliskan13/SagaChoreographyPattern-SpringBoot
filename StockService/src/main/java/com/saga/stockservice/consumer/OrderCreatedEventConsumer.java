package com.saga.stockservice.consumer;

import com.saga.shared.OrderCreatedEvent;
import com.saga.shared.StockNotReservedEvent;
import com.saga.shared.StockReservedEvent;
import com.saga.stockservice.service.PublishService;
import com.saga.stockservice.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderCreatedEventConsumer {
    private final StockService stockService;
    private final PublishService publishService;

    public OrderCreatedEventConsumer(StockService stockService, PublishService publishService) {
        this.stockService = stockService;
        this.publishService = publishService;
    }


    @RabbitListener(queues = "${spring.rabbitmq.queue-names.stock-orderCreated}")
    public void handleMessage(OrderCreatedEvent event) {
        //check stock if exist --> publish Reserved
        //check stock if not exist --> public NotReserved
        Map<Boolean, List<String>> result = stockService.isStockReserved(event);
        try {
            result.get(false);
            StockNotReservedEvent stockNotReservedEvent = new StockNotReservedEvent();
            BeanUtils.copyProperties(event,stockNotReservedEvent);
            stockNotReservedEvent.setErrorMessages(result.get(false));
            publishService.stockNotReservedEventPublish(stockNotReservedEvent);
            log.info("Stock Not Reserved "+ result.get(false).get(0));
        }
        catch (NullPointerException e){
            StockReservedEvent stockReservedEvent = new StockReservedEvent();
            BeanUtils.copyProperties(event,stockReservedEvent);
            publishService.stockReservedEventPublish(stockReservedEvent);
            log.info("Stock reserved!");
        }

    }
}
