package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationTimeoutInMillisExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "timeoutInMillis";
  public static final int DEFAULT = 29000;

  private Integer timeoutInMillis;

  public IntegrationTimeoutInMillisExtension(Integer timeoutInMillis) {
    this.timeoutInMillis = Objects.requireNonNullElse(timeoutInMillis, DEFAULT);
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationTimeoutInMillisExtension timeoutInMillis(Integer timeoutInMillis) {
    this.timeoutInMillis = Objects.requireNonNullElse(timeoutInMillis, DEFAULT);
    return this;
  }

  public Integer timeoutInMillis() {
    return timeoutInMillis;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, String.valueOf(timeoutInMillis));
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(timeoutInMillis);
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationTimeoutInMillisExtension)) return false;
    IntegrationTimeoutInMillisExtension that = (IntegrationTimeoutInMillisExtension) object;
    return timeoutInMillis.equals(that.timeoutInMillis);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timeoutInMillis);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationTimeoutInMillisExtension.class.getSimpleName() + "[", "]")
        .add("timeoutInMillis=" + timeoutInMillis)
        .toString();
  }

  public static class Builder {
    private Integer timeoutInMillis;

    Builder() {
      timeoutInMillis = DEFAULT;
    }

    public Builder timeoutInMillis(Integer timeoutInMillis) {
      this.timeoutInMillis = timeoutInMillis;
      return this;
    }

    public IntegrationTimeoutInMillisExtension build() {
      return new IntegrationTimeoutInMillisExtension(timeoutInMillis);
    }
  }
}
