package com.waytoodanny.licensingservice.service.impl;

import com.waytoodanny.licensingservice.adapter.jpa.License;
import com.waytoodanny.licensingservice.service.LicenceService;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class CircuitBreakerWrapped implements LicenceService {

  private final LicenceService delegate;
  private final CircuitBreaker circuitBreaker;

  @Override
  public List<License> organizationLicenses(String organizationId) {
    log.debug("Wrapping method call into circuit breaker");
    return circuitBreaker.run(
        () -> delegate.organizationLicenses(organizationId)
    );
  }

  @Override
  public void save(License license) {
    log.debug("Wrapping method call into circuit breaker");
    circuitBreaker.run(() -> {
      delegate.save(license);
      return null;
    });
  }

  @Override
  public Optional<License> licence(String organizationId,
                                   String licenseId,
                                   OrganizationServiceClient.Type clientType) {
    log.debug("Wrapping method call into circuit breaker");
    return circuitBreaker.run(
        () -> delegate.licence(organizationId, licenseId, clientType)
    );
  }
}
