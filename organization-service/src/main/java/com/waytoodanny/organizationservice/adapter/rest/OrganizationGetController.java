package com.waytoodanny.organizationservice.adapter.rest;

import com.waytoodanny.organizationservice.adapter.jpa.Organization;
import com.waytoodanny.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value="v1/organizations")
@AllArgsConstructor
public class OrganizationGetController {

  private final OrganizationService organizationService;

  @GetMapping(value="/{organizationId}")
  public Optional<Organization> getOrganization(@PathVariable("organizationId") String organizationId) {
    return organizationService.organization(organizationId);
  }
}
