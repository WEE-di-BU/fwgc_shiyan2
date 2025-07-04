package ynu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ynu.edu.rule.CustomThreeTimeLoadBalanceConfig;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClient(name="provider-service",configuration = CustomThreeTimeLoadBalanceConfig.class)
public class ConsumerApplication16000 {
//    @Bean
//    public RestTemplate getRestTemplate(){return new RestTemplate();}
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication16000.class, args);
    }
}
