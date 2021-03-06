package com.waytoodanny.licensingservice.service.impl;

import com.waytoodanny.licensingservice.adapter.config.ServiceProperties;
import com.waytoodanny.licensingservice.adapter.jpa.License;
import com.waytoodanny.licensingservice.adapter.jpa.LicenseRepository;
import com.waytoodanny.licensingservice.domain.Organization;
import com.waytoodanny.licensingservice.service.LicenceService;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
public class LicenseServiceImpl implements LicenceService {

  private final LicenseRepository licenseRepository;
  private final Map<OrganizationServiceClient.Type, OrganizationServiceClient> organizationServicesByType;
  private final ServiceProperties properties;

  public License license(String organizationId, String licenseId) {
    return licenseRepository.findByOrganizationIdAndId(organizationId, licenseId)
        .orElse(new License())
        .setComment(properties.getComment());
  }

  public List<License> organizationLicenses(String organizationId) {
    return licenseRepository.findByOrganizationId(organizationId);
  }

  public void save(License license) {
    license.setId(UUID.randomUUID().toString());
    licenseRepository.save(license);
  }

  public Optional<License> licence(String organizationId,
                                   String licenseId,
                                   OrganizationServiceClient.Type clientType) {
    Optional<License> licenseMaybe =
        licenseRepository.findByOrganizationIdAndId(organizationId, licenseId);

    OrganizationServiceClient client = organizationServicesByType.get(clientType);
    Optional<Organization> organizationMaybe = client.organization(organizationId);

    return licenseMaybe
        .map(license ->
            organizationMaybe.map(organization ->
                license
                    .withOrganizationName(organization.getName())
                    .withContactName(organization.getContactName())
                    .withContactEmail(organization.getContactEmail())
                    .withContactPhone(organization.getContactPhone())
                    .withComment(properties.getComment())
            ).orElse(license)
        );
  }
}
