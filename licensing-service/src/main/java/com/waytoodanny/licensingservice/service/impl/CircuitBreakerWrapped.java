package com.waytoodanny.licensingservice.service.impl;

import com.waytoodanny.licensingservice.adapter.jpa.License;
import com.waytoodanny.licensingservice.service.LicenceService;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    return CircuitBreaker.decorateSupplier(
        circuitBreaker, () -> delegate.organizationLicenses(organizationId)
    ).get();
  }

  @Override
  public void save(License license) {
    log.debug("Wrapping method call into circuit breaker");
    circuitBreaker.decorateRunnable(() -> delegate.save(license)).run();
  }

  @Override
  public Optional<License> licence(String organizationId,
                                   String licenseId,
                                   OrganizationServiceClient.Type clientType) {
    log.debug("Wrapping method call into circuit breaker");
    return CircuitBreaker.decorateSupplier(
        circuitBreaker, () -> delegate.licence(organizationId, licenseId, clientType)
    ).get();
  }
}
