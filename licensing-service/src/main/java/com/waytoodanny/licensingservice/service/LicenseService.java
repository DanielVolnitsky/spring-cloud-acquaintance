package com.waytoodanny.licensingservice.service;

import com.waytoodanny.licensingservice.adapter.config.ServiceProperties;
import com.waytoodanny.licensingservice.adapter.jpa.License;
import com.waytoodanny.licensingservice.adapter.jpa.LicenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LicenseService {

  private final LicenseRepository licenseRepository;
  private final ServiceProperties properties;

  public License license(String organizationId, String licenseId) {
    return licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId)
        .orElse(new License())
        .setComment(properties.getComment());
  }

  public List<License> organizationLicenses(String organizationId) {
    return licenseRepository.findByOrganizationId(organizationId);
  }

  public void save(License license) {
    license.setLicenseId(UUID.randomUUID().toString());
    licenseRepository.save(license);
  }
}
