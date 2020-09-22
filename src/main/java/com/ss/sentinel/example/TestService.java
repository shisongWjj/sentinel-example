package com.ss.sentinel.example;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * TestService
 *
 * @author shisong
 * @date 2020/9/22
 */
@Service
public class TestService {

    @SentinelResource(value = "doTest")
    public String sayHello(String name) {
        return "say hello " + name;
    }
}
