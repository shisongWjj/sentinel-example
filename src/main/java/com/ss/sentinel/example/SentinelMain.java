package com.ss.sentinel.example;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * SentinelMain
 *
 * @author shisong
 * @date 2020/9/22
 */
public class SentinelMain {

    public static void main(String[] args){
        //在判断能否获取到访问某个资源的资格之前
        //先要对这个资源设定限流条件
        initFlowRule();
        while (true){
            //当大量请求访问的时候，判断是否能获取到访问当前资源的资格（限流）
            try (Entry helloWorld = SphU.entry("hello world")){
                //获取到了资格
                System.out.println("请求被接受");
            }catch (Exception e){
                //没获取到资格
                System.out.println("请求被拒绝");
            }
        }
    }

    /**
     * 对资源设置限流规则
     * 可以对同一资源设置不同的规则
     * 也可以对不同资源设置不同/相同的规则
     */
    private static void initFlowRule(){
        List<FlowRule> rules=new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        //设置对哪个资源进行限流
        flowRule.setResource("hello world");
        //两种规则 qps thread
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //如果是QPS的话  设置这个资源在某一时刻最多有多少的访问量
        //如果是thread的话  设置这个资源在同时支持多少个线程对这个资源做处理
        flowRule.setCount(5);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }

}
