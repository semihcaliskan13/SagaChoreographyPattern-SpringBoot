package com.saga.shared;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentFailedEvent {
    private Long orderId;
    private String message = "Your payment is failed!";
    private List<OrderItemMessage> orderItems;
}
