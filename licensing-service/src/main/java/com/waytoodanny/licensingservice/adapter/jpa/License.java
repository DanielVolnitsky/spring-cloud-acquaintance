package com.waytoodanny.licensingservice.adapter.jpa;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "licenses")
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class License {

  @Id
  @Column(name = "license_id", nullable = false)
  private String licenseId;

  @Column(name = "organization_id", nullable = false)
  private String organizationId;

  @Transient
  @With
  private String organizationName = "";

  @Transient
  @With
  private String contactName = "";

  @Transient
  @With
  private String contactPhone = "";

  @Transient
  @With
  private String contactEmail = "";

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "license_type", nullable = false)
  private String licenseType;

  @Column(name = "license_max", nullable = false)
  private Integer licenseMax;

  @Column(name = "license_allocated", nullable = false)
  private Integer licenseAllocated;

  @Column(name = "comment")
  @With
  private String comment;
}
