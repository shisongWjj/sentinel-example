package com.ss.sentinel.example;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SentinelExampleApplication {

    public static void main(String[] args) {
        initFlowRule();
        SpringApplication.run(SentinelExampleApplication.class, args);
    }

    private static void initFlowRule(){
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setCount(2);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setResource("doTest");
        flowRules.add(flowRule);
        FlowRuleManager.loadRules(flowRules);
    }

}
