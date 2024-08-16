package com.myshop.order.command.infrastructure;

import com.myshop.order.command.domain.Customer;
import com.myshop.order.command.domain.Money;
import com.myshop.order.command.domain.OrderLine;

import java.util.List;

public interface RuleDiscounter {
    Money applyRules(Customer cusoterm, List<OrderLine> orderLines);
}
