package com.waytoodanny.licensingservice.service;

import com.waytoodanny.licensingservice.adapter.config.ServiceProperties;
import com.waytoodanny.licensingservice.adapter.jpa.License;
import com.waytoodanny.licensingservice.adapter.jpa.LicenseRepository;
import com.waytoodanny.licensingservice.domain.Organization;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class LicenseService {

  private final LicenseRepository licenseRepository;
  private final OrganizationServiceClient organizationDiscoveryClient;
  private final OrganizationServiceClient organizationRestClient;
  private final OrganizationServiceClient organizationFeignClient;
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
                                   String clientType) {
    Optional<License> licenseMaybe =
        licenseRepository.findByOrganizationIdAndId(organizationId, licenseId);

    Optional<Organization> organizationMaybe =
        organization(organizationId, clientType);

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

  private Optional<Organization> organization(String organizationId, String clientType) {
    switch (clientType) {
      case "discovery":
        log.info("Fetching organization info via discovery client");
        return organizationDiscoveryClient.organization(organizationId);
      case "rest":
        log.info("Fetching organization info via load balanced rest template");
        return organizationRestClient.organization(organizationId);
      case "feign":
        log.info("Fetching organization info via feign");
        return organizationFeignClient.organization(organizationId);
      default:
        return organizationRestClient.organization(organizationId);
    }
  }
}
