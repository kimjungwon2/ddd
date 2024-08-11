package com.myshop.order.command.domain;


public class Order {
    private OrderState state;
    private ShippingInfo shippingInfo;

    public void changeShippingInfo(ShippingInfo newShippingInfo){
        if(!isShippingChangeable()){
            throw new IllegalStateException("Can't change shipping in" + state);
        }
        this.shippingInfo = newShippingInfo;
    }

    private boolean isShippingChangeable(){
        return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
    }

    public void changeShipped(){

    }

    public void cancel(){

    }

    public void completePayment(){

    }

}
