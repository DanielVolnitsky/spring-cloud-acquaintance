package com.waytoodanny.licensingservice.adapter.config;

import com.waytoodanny.licensingservice.adapter.client.OrganizationRestTemplateClient;
import com.waytoodanny.licensingservice.adapter.jpa.LicenseRepository;
import com.waytoodanny.licensingservice.service.LicenceService;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;
import com.waytoodanny.licensingservice.service.impl.CircuitBreakerWrapped;
import com.waytoodanny.licensingservice.service.impl.LicenseServiceImpl;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Configuration
@Slf4j
public class ServiceConfig {

  @Bean
  @LoadBalanced
  public RestTemplate discoveryAwareRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public OrganizationServiceClient organizationRestTemplateClient(RestTemplate discoveryAwareRestTemplate) {
    return new OrganizationRestTemplateClient(discoveryAwareRestTemplate);
  }

  @Bean
  public Map<OrganizationServiceClient.Type, OrganizationServiceClient>
  organizationServiceClientsByType(Collection<OrganizationServiceClient> clients) {
    log.info("Registered " + clients.size() + " organization service clients");
    return clients.stream()
        .collect(toMap(OrganizationServiceClient::type, client -> client));
  }

  @Bean
  public LicenceService
  licenceService(LicenseRepository licenseRepository,
                 Map<OrganizationServiceClient.Type, OrganizationServiceClient> organizationServicesByType,
                 ServiceProperties serviceProperties,
                 CircuitBreakerRegistry circuitBreakerRegistry) {

    return new CircuitBreakerWrapped(
        new LicenseServiceImpl(licenseRepository, organizationServicesByType, serviceProperties),
        circuitBreakerRegistry.circuitBreaker("licenseService")
    );
  }
}
