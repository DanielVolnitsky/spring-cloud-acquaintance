package com.waytoodanny.licensingservice.service;

import com.waytoodanny.licensingservice.adapter.jpa.License;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;

import java.util.List;
import java.util.Optional;

public interface LicenceService {

  List<License> organizationLicenses(String organizationId);

  void save(License license);

  Optional<License> licence(String organizationId,
                            String licenseId,
                            OrganizationServiceClient.Type clientType);
}
