package com.ogasimov.jug.live.mooddecorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.AbstractMap;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableBinding(Sink.class)
public class MoodDecoratorService {
    @Autowired
    private MoodClient moodClient;

    @Autowired
    private HelloDataStoreClient dataStore;

    @StreamListener(Sink.INPUT)
    private void decorateMood(AbstractMap.SimpleEntry<String, String> pair) {
        final String name = pair.getKey();
        String result = pair.getValue();
        try {
            String mood = moodClient.getMood(name);
            result += ". I know your mood is '" + mood + "'.";
        } catch (Exception ex) {
            result += ". Sorry, I could not fetch your mood. Exception: " + ex.getMessage();
        }
        dataStore.setHello(name, result);
    }

    public static void main(String... args) {
        SpringApplication.run(MoodDecoratorService.class, args);
    }
}
