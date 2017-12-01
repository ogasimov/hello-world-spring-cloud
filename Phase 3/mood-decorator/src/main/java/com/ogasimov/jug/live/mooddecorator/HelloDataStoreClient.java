package com.ogasimov.jug.live.mooddecorator;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "Hello")
public interface HelloDataStoreClient {
    @PostMapping("/hello")
    void setHello(@RequestParam("name") String name, @RequestParam("result") String result);
}
