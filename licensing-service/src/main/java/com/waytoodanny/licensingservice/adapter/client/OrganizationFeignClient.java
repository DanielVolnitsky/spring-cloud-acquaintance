package com.waytoodanny.licensingservice.adapter.client;

import com.waytoodanny.licensingservice.domain.Organization;
import com.waytoodanny.licensingservice.service.client.OrganizationServiceClient;
import org.aspectj.weaver.ast.Or;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Component
@FeignClient("organizationservice")
public interface OrganizationFeignClient extends OrganizationServiceClient {

  @RequestMapping(
      method = RequestMethod.GET,
      value = "/v1/organizations/{organizationId}",
      consumes = "application/json"
  )
  Optional<Organization> organization(@PathVariable("organizationId") String organizationId);

  @Override
  default Type type() {
    return Type.FEIGN;
  };
}
