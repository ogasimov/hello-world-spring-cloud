package com.ogasimov.jug.live.moodservice;

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
public class MoodApp {
    private static final Map<String, String> moodMap = new ConcurrentHashMap<>();

    public static void main(String... args) {
        SpringApplication.run(MoodApp.class, args);
    }

    @PostMapping("/mood")
    private void setMood(@RequestParam String name, @RequestParam String mood) {
        moodMap.put(name.toLowerCase(), mood);
    }

    @GetMapping("/mood")
    private ResponseEntity<String> getMood(@RequestParam String name) {
        name = name.toLowerCase();
        if (moodMap.containsKey(name)) {
            return ResponseEntity.ok(moodMap.get(name));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
