package com.myshop.order.command.infrastructure;

import com.myshop.order.command.domain.Customer;
import com.myshop.order.command.domain.Money;
import com.myshop.order.command.domain.OrderLine;

import java.util.List;

public class DroolsRuleDiscounter implements RuleDiscounter{

    private kieContainer kContainer;

    public DroolsRuleEngine(){
        kieServices ks = kieServices.Factory.get();
        kContainer = ks.getKieClasspathConatiner();
    }

    @Override
    public Money applyRules(Customer cusoterm, List<OrderLine> orderLines) {
        kieSession kSession = kContainer.newKieSession("discountSession");

        try{
            kSession.fireAllRules();
        }finally {
            kSession.dipose();
        }
        return money.toImmutableMoney();
    }


}
