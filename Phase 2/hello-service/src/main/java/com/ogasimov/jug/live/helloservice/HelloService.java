package com.ogasimov.jug.live.helloservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableDiscoveryClient
public class HelloService {
    @Autowired
    private MoodClient moodClient;

    @GetMapping("/hello")
    private String getHello(@RequestParam String name) {
        String result = getHelloDecorated(name);
        try {
            String mood = moodClient.getMood(name);
            result += ". I know your mood is '" + mood + "'.";
        } catch (Exception ex) {
            result += ". Sorry, I could not fetch your mood. Exception: " + ex.getMessage();
        }
        return result;
    }

    public String getHelloDecorated(String name) {
        return "Hello " + name;
    }

    public static void main(String... args) {
        SpringApplication.run(HelloService.class, args);
    }
}
