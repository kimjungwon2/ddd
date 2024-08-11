package com.myshop.order.command.domain;


import java.util.List;

public class Order {

    private List<OrderLine> orderLines;
    private Money totalAmounts;

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    private OrderState state;
    private ShippingInfo shippingInfo;

    public void changeShippingInfo(ShippingInfo newShippingInfo){
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
    }

    private void verifyNotYetShipped() {
        if(state!=OrderState.PAYMENT_WAITING && state != OrderState.PREPARING){
            throw new IllegalStateException("aleady shipped");
        }
    }

    private boolean isShippingChangeable(){
        return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
    }

    public void changeShipped(){

    }

    public void cancel(){
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
    }

    public void completePayment(){

    }

    private void setOrderLines(List<OrderLine> orderLines){
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines){
        if(orderLines == null || orderLines.isEmpty()){
            throw new IllegalStateException("no OrderLine");
        }
    }

    private void calculateTotalAmounts(){
        int sum = orderLines.stream()
                .mapToInt(x->x.getAmounts())
                .sum();
        this.totalAmounts = new Money(sum);

    }


    private void setShippingInfo(ShippingInfo shippingInfo) {
        if(shippingInfo == null)
            throw new IllegalArgumentException("no shippingInfo");
        this.shippingInfo = shippingInfo;
    }

}
