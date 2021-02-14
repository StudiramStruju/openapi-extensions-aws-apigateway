package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationType;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationIntegrationTypeExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "type";

  private final IntegrationType integrationType;

  public IntegrationIntegrationTypeExtension(IntegrationType integrationType) {
    this.integrationType = integrationType;
  }

  public IntegrationType integrationType() {
    return integrationType;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, integrationType.key());
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(integrationType);
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationIntegrationTypeExtension)) return false;
    IntegrationIntegrationTypeExtension that = (IntegrationIntegrationTypeExtension) object;
    return integrationType == that.integrationType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(integrationType);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationIntegrationTypeExtension.class.getSimpleName() + "[", "]")
        .add("integrationType=" + integrationType)
        .toString();
  }
}
