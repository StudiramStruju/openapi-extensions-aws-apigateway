package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationType;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationIntegrationTypeExtension implements ApiGatewayIntegrationExtension<String> {

  public static final String NAME = "type";

  private final IntegrationType integrationType;

  public IntegrationIntegrationTypeExtension(IntegrationType integrationType) {
    this.integrationType = integrationType;
  }

  public IntegrationType getIntegrationType() {
    return integrationType;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  public String getExtensionValue() {
    return integrationType.key();
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
