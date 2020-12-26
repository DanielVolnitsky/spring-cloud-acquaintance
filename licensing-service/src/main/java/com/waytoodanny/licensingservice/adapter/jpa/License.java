package com.waytoodanny.licensingservice.adapter.jpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licenses")
@Data
public class License {

  @Id
  @Column(name = "license_id", nullable = false)
  private String licenseId;
  @Column(name = "organization_id", nullable = false)
  private String organizationId;
  @Column(name = "product_name", nullable = false)
  private String productName;
  @Column(name="comment")
  private String comment;
}
