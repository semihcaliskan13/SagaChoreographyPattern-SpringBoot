package com.saga.shared;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockNotReservedEvent {
    private Long orderId;
    private Long userId;
    private String message;
}
