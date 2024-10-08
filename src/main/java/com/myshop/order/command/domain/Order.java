package com.myshop.order.command.domain;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table("TBL_ORDER")
public class Order {

    private String orderNumber;
    private List<OrderLine> orderLines;
    private Money totalAmounts;
    private OrderNo number;
    private Orderer orderer;
    private OrderState state;
    private ShippingInfo shippingInfo;

    public Order(Orderer orderer, List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderer(orderer);
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }


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

    private void setOrderer(Orderer orderer){
        if (orderer == null) throw new IllegalArgumentException("no orderer");
        this.orderer = orderer;
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(obj.getClass()!=Order.class) return false;

        Order order = (Order)obj;
        if ( this.orderNumber == null) return false;

        return this.orderNumber.equals(order.orderNumber);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result =1;
        result = prime * result + ((this.orderNumber == null) ? 0 : this.orderNumber.hashCode());
        return result;
    }
}
