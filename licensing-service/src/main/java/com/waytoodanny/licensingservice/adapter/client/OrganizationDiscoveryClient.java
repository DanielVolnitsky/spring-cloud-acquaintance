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
public class OrganizationDiscoveryClient implements OrganizationServiceClient {

  private final DiscoveryClient discoveryClient;

  public Optional<Organization> organization(String organizationId) {
    List<ServiceInstance> instances = discoveryClient.getInstances("organizationservice");
    if (instances.size() == 0) {
      return Optional.empty();
    }

    /*
     * All RestTemplates managed by the Spring framework will have a Ribbon-enabled interceptor
     * injected into them that will change how URLs are created with the RestTemplate class.
     * Directly instantiating the RestTemplate class allows us to avoid this behavior.
     * */
    RestTemplate restTemplate = new RestTemplate();
    String serviceUri = String.format(
        "%s/v1/organizations/%s",
        instances.get(0).getUri().toString(),
        organizationId
    );

    ResponseEntity<Organization> response =
        restTemplate.exchange(
            serviceUri,
            HttpMethod.GET,
            null, Organization.class, organizationId
        );

    return Optional.ofNullable(response.getBody());
  }
}
