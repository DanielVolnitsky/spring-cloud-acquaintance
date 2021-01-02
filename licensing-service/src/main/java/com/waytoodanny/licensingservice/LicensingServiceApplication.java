package com.waytoodanny.licensingservice;

import com.waytoodanny.licensingservice.adapter.config.ServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // to enable the application to use the DiscoveryClient and Ribbon libraries
@EnableConfigurationProperties(ServiceProperties.class)
public class LicensingServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(LicensingServiceApplication.class, args);
  }
}
