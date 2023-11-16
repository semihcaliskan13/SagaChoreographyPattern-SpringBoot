package com.saga.orderservice.util;

import com.saga.orderservice.dto.OrderItemDto;

import java.math.BigDecimal;
import java.util.List;

public class OrderTotalPriceCalculatorUtil {

    public static BigDecimal calculateTotalPrice(List<OrderItemDto> orderItemDtos) {
        return orderItemDtos.stream()
                .map(orderItem -> orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
