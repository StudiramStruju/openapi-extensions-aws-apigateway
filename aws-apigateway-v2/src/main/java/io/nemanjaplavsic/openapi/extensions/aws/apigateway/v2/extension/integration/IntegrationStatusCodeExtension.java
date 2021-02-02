package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationStatusCodeExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "statusCode";

  private final Integer statusCode;

  public IntegrationStatusCodeExtension(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Integer statusCode() {
    return statusCode;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, statusCode.toString());
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(statusCode);
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationStatusCodeExtension)) return false;
    IntegrationStatusCodeExtension that = (IntegrationStatusCodeExtension) object;
    return statusCode.equals(that.statusCode);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationStatusCodeExtension.class.getSimpleName() + "[", "]")
        .add("statusCode=" + statusCode)
        .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode);
  }

  public static class Builder {
    private Integer statusCode;

    Builder() {
    }

    public Builder statusCode(Integer statusCode) {
      this.statusCode = statusCode;
      return this;
    }

    public IntegrationStatusCodeExtension build() {
      return new IntegrationStatusCodeExtension(statusCode);
    }
  }
}
