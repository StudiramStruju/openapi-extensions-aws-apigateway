package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationType;
import org.springframework.lang.Nullable;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationIntegrationTypeExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "type";

  private IntegrationType integrationType;

  public IntegrationIntegrationTypeExtension(IntegrationType integrationType) {
    this.integrationType = integrationType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationIntegrationTypeExtension integrationType(@Nullable IntegrationType integrationType) {
    if (Objects.nonNull(integrationType))
      this.integrationType = integrationType;
    return this;
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

  public static class Builder {
    private IntegrationType integrationType;

    Builder() {
      integrationType = IntegrationType.AWS;
    }

    public Builder integrationType(IntegrationType integrationType) {
      this.integrationType = integrationType;
      return this;
    }

    public IntegrationIntegrationTypeExtension build() {
      return new IntegrationIntegrationTypeExtension(integrationType);
    }
  }
}
