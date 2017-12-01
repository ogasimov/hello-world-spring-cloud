package helloworldservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "Hello")
public interface HelloClient {
    @GetMapping("/hello")
    String getHello(@RequestParam("name") String name);
}
