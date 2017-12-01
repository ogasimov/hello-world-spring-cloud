package com.ogasimov.jug.live.hellocommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableDiscoveryClient
@EnableBinding(Source.class)
public class HelloCommandService {
    @Autowired
    MessageChannel output;

    @PostMapping("/hello")
    private ResponseEntity putHello(@RequestParam String name) {
        output.send(MessageBuilder.withPayload(name).build());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/hello?name=" + name);
        return new ResponseEntity(headers, HttpStatus.SEE_OTHER);
    }

    public static void main(String... args) {
        SpringApplication.run(HelloCommandService.class, args);
    }
}
