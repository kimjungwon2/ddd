package com.myshop.order.command.application;

import com.myshop.order.command.domain.Money;
import com.myshop.order.command.domain.OrderLine;
import com.myshop.order.command.infrastructure.DroolsRuleEngine;

import java.util.Arrays;
import java.util.List;

public class CalculateDiscountService {
    private DroolsRuleEngine ruleEngine;

    public CalculateDiscountService(DroolsRuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    public Money calculateDiscount(List<OrderLine> orderLines, String customerId){
        Customer customer = findCustomer(customerId);

        MutableMoney money = new MutableMoney(0);
        List<?> facts = Arrays.asList(customer, money);
        facts.addAll(orderLines);
        ruleEngine.evalute("discountCalculation", facts);
        return money.toImmutableMoney();
    }

}
