package com.ss.sentinel.example;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * TestService
 *
 * @author shisong
 * @date 2020/9/22
 */
@Service
public class TestService {

    @SentinelResource(value = "doTest" ,blockHandler = "testBlockHandler" ,fallback = "testFallBack")
    public String sayHello(String name) {
        return "say hello " + name;
    }

    public String testBlockHandler(String name, BlockException e){
        //限流引起的
        return "请求已被限流";
    }

    public String testFallBack(String name){
        //熔断引起的
        return "请求已被熔断";
    }
}
