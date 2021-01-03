package com.waytoodanny.licensingservice.service.client;

import com.waytoodanny.licensingservice.domain.Organization;

import java.util.Optional;

public interface OrganizationServiceClient {

  Optional<Organization> organization(String organizationId);

  Type type();

  enum Type {
    DISCOVERY, REST, FEIGN
  }
}
