package com.waytoodanny.licensingservice.adapter.config;

import com.waytoodanny.licensingservice.adapter.client.OrganizationRestTemplateClient;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class SpringCloudConfig {

  @Bean
  @LoadBalanced
  public RestTemplate discoveryAwareRestTemplate(){
    return new RestTemplate();
  }

  @Bean
  public OrganizationServiceClient organizationRestTemplateClient(RestTemplate discoveryAwareRestTemplate) {
      return new OrganizationRestTemplateClient(discoveryAwareRestTemplate);
  }
}
