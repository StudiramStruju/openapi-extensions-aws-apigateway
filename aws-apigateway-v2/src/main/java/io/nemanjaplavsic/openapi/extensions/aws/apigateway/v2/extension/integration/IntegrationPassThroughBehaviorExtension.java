package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.PassThroughBehavior;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationPassThroughBehaviorExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "passthroughBehavior";

  private PassThroughBehavior passThroughBehavior;

  public IntegrationPassThroughBehaviorExtension(PassThroughBehavior passThroughBehavior) {
    this.passThroughBehavior = Objects.requireNonNullElse(passThroughBehavior, PassThroughBehavior.NONE);
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationPassThroughBehaviorExtension passThroughBehavior(PassThroughBehavior passThroughBehavior) {
    this.passThroughBehavior = Objects.requireNonNullElse(passThroughBehavior, PassThroughBehavior.NONE);
    return this;
  }

  public PassThroughBehavior passThroughBehavior() {
    return passThroughBehavior;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, passThroughBehavior.key());
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(passThroughBehavior);
      return Objects.nonNull(passThroughBehavior.key());
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationPassThroughBehaviorExtension)) return false;
    IntegrationPassThroughBehaviorExtension that = (IntegrationPassThroughBehaviorExtension) object;
    return passThroughBehavior == that.passThroughBehavior;
  }

  @Override
  public int hashCode() {
    return Objects.hash(passThroughBehavior);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationPassThroughBehaviorExtension.class.getSimpleName() + "[", "]")
        .add("passThroughBehavior=" + passThroughBehavior)
        .toString();
  }

  public static class Builder {
    private PassThroughBehavior passThroughBehavior;

    Builder() {
      passThroughBehavior = PassThroughBehavior.NONE;
    }

    public Builder passThroughBehavior(PassThroughBehavior passThroughBehavior) {
      this.passThroughBehavior = passThroughBehavior;
      return this;
    }

    public IntegrationPassThroughBehaviorExtension build() {
      return new IntegrationPassThroughBehaviorExtension(passThroughBehavior);
    }
  }
}
