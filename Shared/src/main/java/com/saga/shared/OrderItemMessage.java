package com.saga.shared;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemMessage {
    private Long productId;
    private Long count;
    private BigDecimal price;
}
