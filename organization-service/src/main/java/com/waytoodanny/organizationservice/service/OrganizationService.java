package com.waytoodanny.organizationservice.service;

import com.waytoodanny.organizationservice.adapter.jpa.Organization;
import com.waytoodanny.organizationservice.adapter.jpa.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrganizationService {

  private final OrganizationRepository repository;

  public Optional<Organization> organization(String organizationId) {
    return repository.findById(organizationId);
  }

  public void save(Organization org) {
    org.setId(UUID.randomUUID().toString());
    repository.save(org);
  }
}
