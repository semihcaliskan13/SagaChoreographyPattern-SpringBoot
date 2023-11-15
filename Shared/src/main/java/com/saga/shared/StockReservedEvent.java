package com.saga.shared;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockReservedEvent {
    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private List<OrderItemMessage> orderItems;
}
