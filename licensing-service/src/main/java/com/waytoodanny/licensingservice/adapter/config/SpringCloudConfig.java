package com.waytoodanny.licensingservice.adapter.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class SpringCloudConfig {

  @Bean
  @LoadBalanced
  public RestTemplate discoveryAwareRestTemplate(){
    return new RestTemplate();
  }
}
