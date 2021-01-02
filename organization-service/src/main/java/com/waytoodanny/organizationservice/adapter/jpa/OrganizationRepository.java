package com.waytoodanny.organizationservice.adapter.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String> {

  Optional<Organization> findById(String organizationId);
}
