package com.waytoodanny.licensingservice.adapter.rest;

import com.waytoodanny.licensingservice.adapter.jpa.License;
import com.waytoodanny.licensingservice.service.LicenceService;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/licenses")
@AllArgsConstructor
public class OrganizationLicenseController {

  private final LicenceService licenseService;

  @GetMapping(value = "/{licenseId}/{clientType}")
  public Optional<License> getLicenseViaSpecificClient(@PathVariable("organizationId") String organizationId,
                                                       @PathVariable("licenseId") String licenseId,
                                                       @PathVariable("clientType") OrganizationServiceClient.Type clientType) {

    return licenseService.licence(organizationId, licenseId, clientType);
  }
}
