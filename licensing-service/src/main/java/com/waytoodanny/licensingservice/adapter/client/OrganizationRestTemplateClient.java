package com.waytoodanny.licensingservice.adapter.client;

import com.waytoodanny.licensingservice.domain.Organization;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class OrganizationRestTemplateClient implements OrganizationServiceClient {

  private final RestTemplate discoveryAwareRestTemplate;

  public Optional<Organization> organization(String organizationId) {
      ResponseEntity<Organization> restResponse =
          discoveryAwareRestTemplate.exchange(
              "http://organizationservice/v1/organizations/{organizationId}",
              HttpMethod.GET,
              null, Organization.class, organizationId
          );

      return Optional.ofNullable(restResponse.getBody());
  }
}
