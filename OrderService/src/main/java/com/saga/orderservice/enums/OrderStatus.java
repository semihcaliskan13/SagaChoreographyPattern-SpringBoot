package com.saga.orderservice.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    Suspend("Suspend"),
    Completed("Completed"),
    Fail("Fail");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
