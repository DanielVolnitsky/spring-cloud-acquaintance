package com.waytoodanny.licensingservice.adapter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("licensing-service")
@Data
public class ServiceProperties {
  private String comment;
}
