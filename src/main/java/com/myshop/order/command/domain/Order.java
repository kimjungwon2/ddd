package com.myshop.order.command.domain;




public class Order {
    private OrderState state;
    private ShippingInfo shippingInfo;

    public void changeShippingInfo(ShippingInfo newShippingInfo){
        if(!state.isShippingChangeable()){
            throw new IllegalStateException("Can't change shipping in" + state);
        }
        this.shippingInfo = newShippingInfo;
    }
}
