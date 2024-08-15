package com.myshop.order.command.application;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(String orderId) {
    }
}
