package com.ogasimov.jug.live.helloservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "Mood")
public interface MoodClient {
    @GetMapping("/mood")
    String getMood(@RequestParam("name") String name) throws Exception;
}
