package helloworldservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableDiscoveryClient
public class HelloWorldService {
    @Autowired
    private HelloClient helloClient;

    @GetMapping("/hello-world")
    private String getHelloWorld() {
        return helloClient.getHello("World");
    }

    public static void main(String... args) {
        SpringApplication.run(HelloWorldService.class, args);
    }
}
