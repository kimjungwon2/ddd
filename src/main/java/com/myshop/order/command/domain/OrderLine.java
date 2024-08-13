package com.myshop.order.command.domain;

public class OrderLine {
    private Product product;
    private Money price;
    private int quantity;
    private int amounts;

    public OrderLine(Product product, Money price, int quantity, int amounts) {
        this.product = product;
        this.price = new Money(price.getValue());
        this.quantity = quantity;
        this.amounts = amounts;
    }

    private int calculateAmounts(){
        return price * quantity;
    }

    public int getAmounts(){
        return amounts;
    }

}
