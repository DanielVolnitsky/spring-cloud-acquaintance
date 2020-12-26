package com.waytoodanny.licensingservice.adapter.rest;

import com.waytoodanny.licensingservice.adapter.jpa.License;
import com.waytoodanny.licensingservice.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/licenses")
@AllArgsConstructor
public class OrganizationLicenseController {

  private final LicenseService licenseService;

  @GetMapping(value = "/{licenseId}")
  public License organizationLicense(@PathVariable("organizationId") String organizationId,
                                     @PathVariable("licenseId") String licenseId) {
    return licenseService.license(organizationId, licenseId);
  }
}
