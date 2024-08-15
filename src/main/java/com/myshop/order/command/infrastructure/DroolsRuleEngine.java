package com.myshop.order.command.infrastructure;

import java.util.List;

public class DroolsRuleEngine {
    private KieContainer kieContainer;

    public void evalute(String sessionName, List<?> facts){
        KieSession kSession = kContainer.newKieSession(sessionName);
        try{
            facts.forEach(x -> kSession.insert(x));
            kSession.fireAllRules();
        } finally{
            kSession.dispose();
        }
    }
}
