package com.waytoodanny.licensingservice.adapter.client;

import com.waytoodanny.licensingservice.domain.Organization;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@AllArgsConstructor
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

  @Override
  public Type type() {
    return Type.REST;
  }
}
