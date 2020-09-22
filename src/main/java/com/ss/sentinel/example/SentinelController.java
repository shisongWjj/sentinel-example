package com.ss.sentinel.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SentinelController
 *
 * @author shisong
 * @date 2020/9/22
 */
@RestController
public class SentinelController {

    @Resource
    private TestService testService;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name){
        return testService.sayHello(name);
    }

}
