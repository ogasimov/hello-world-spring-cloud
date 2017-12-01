package com.ogasimov.jug.live.helloservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class HelloService {
    private static final Map<String, String> dataStore = new ConcurrentHashMap<>();

    @GetMapping("/hello")
    private ResponseEntity<String> getHello(@RequestParam String name) {
        name = name.toLowerCase();
        if (dataStore.containsKey(name)) {
            return ResponseEntity.ok(dataStore.get(name));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/hello")
    private void setHello(@RequestParam("name") String name, @RequestParam("result") String result) {
        dataStore.put(name.toLowerCase(), result);
    }

    public static void main(String... args) {
        SpringApplication.run(HelloService.class, args);
    }
}
