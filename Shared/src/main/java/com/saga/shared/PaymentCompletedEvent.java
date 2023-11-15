package com.saga.shared;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentCompletedEvent {
    private Long orderId;
}
