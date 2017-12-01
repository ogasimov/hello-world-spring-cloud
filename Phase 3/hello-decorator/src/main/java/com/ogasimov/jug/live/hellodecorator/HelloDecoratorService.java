package com.ogasimov.jug.live.hellodecorator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.AbstractMap;

@SpringBootApplication
@EnableBinding(Processor.class)
public class HelloDecoratorService {
    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    private AbstractMap.SimpleEntry<String, String> decorateHello(String name) {
        AbstractMap.SimpleEntry<String, String> pair = new AbstractMap.SimpleEntry<String, String>(name, "Hello " + name);
        return pair;
    }

    public static void main(String... args) {
        SpringApplication.run(HelloDecoratorService.class, args);
    }
}
