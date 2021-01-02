package com.waytoodanny.licensingservice.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Organization {
  String id;
  String name;
  String contactName;
  String contactEmail;
  String contactPhone;
}
