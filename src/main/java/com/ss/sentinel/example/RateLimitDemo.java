package com.ss.sentinel.example;

import com.google.common.util.concurrent.RateLimiter;

/**
 * RateLimitDemo
 *
 * @author shisong
 * @date 2020/9/23
 */
public class RateLimitDemo {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(10);
        if(rateLimiter.tryAcquire()){
            System.out.println("抢到了资源");
        }else {
            System.out.println("请求被拒绝");
        }
    }
}
