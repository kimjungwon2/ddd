package com.myshop.order.command.domain;

import javax.persistence.*;

@Embeddable
public class ShippingInfo {
    private Receiver receiver;
    private Address address;

    public ShippingInfo(Receiver receiver, Address address) {
        this.receiver = receiver;
        this.address = address;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public Address getAddress() {
        return address;
    }
}
