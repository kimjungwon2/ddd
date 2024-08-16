package com.myshop.order.command.infrastructure;

import com.myshop.order.command.domain.Customer;
import com.myshop.order.command.domain.Money;
import com.myshop.order.command.domain.OrderLine;

import java.util.List;

public class CalculateDiscountService {
    private RuleDiscounter ruleDiscounter;

    public CalculateDiscountService(RuleDiscounter ruleDiscounter) {
        this.ruleDiscounter = ruleDiscounter;
    }

    public Money calculateDiscount(List<OrderLine> orderLines, String customerId){
        Customer customer = findCustomer(customerId);
        return ruleDiscounter.applyRules(customer, orderLines);
    }
}
