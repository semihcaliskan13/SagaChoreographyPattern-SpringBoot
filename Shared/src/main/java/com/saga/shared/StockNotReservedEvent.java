package com.saga.shared;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockNotReservedEvent {
    private Long orderId;
    private Long userId;
    private List<String> errorMessages;
}
