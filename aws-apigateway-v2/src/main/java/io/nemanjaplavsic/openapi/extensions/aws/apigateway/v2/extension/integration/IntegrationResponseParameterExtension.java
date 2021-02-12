package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;
import org.springframework.lang.Nullable;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationResponseParameterExtension implements IntegrationExtension<StringVendorExtension> {


  // TODO: 2/12/21 add source (source can be statis... Check annotation)
  @Nullable
  private String methodHeaderName;
  @Nullable
  private IntegrationResponseParameterType integrationParameterType;
  @Nullable
  private String integrationParameterName;

  public IntegrationResponseParameterExtension(@Nullable String methodHeaderName,
                                               @Nullable IntegrationResponseParameterType integrationParameterType,
                                               @Nullable String integrationParameterName) {
    this.methodHeaderName = methodHeaderName;
    this.integrationParameterType = integrationParameterType;
    this.integrationParameterName = integrationParameterName;
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationResponseParameterExtension methodHeaderName(@Nullable String methodHeaderName) {
    this.methodHeaderName = methodHeaderName;
    return this;
  }

  public IntegrationResponseParameterExtension integrationParameterType(@Nullable IntegrationResponseParameterType integrationParameterType) {
    this.integrationParameterType = integrationParameterType;
    return this;
  }

  public IntegrationResponseParameterExtension integrationParameterName(@Nullable String integrationParameterName) {
    this.integrationParameterName = integrationParameterName;
    return this;
  }

  @Nullable
  public String methodHeaderName() {
    return methodHeaderName;
  }

  @Nullable
  public IntegrationResponseParameterType integrationParameterType() {
    return integrationParameterType;
  }

  @Nullable
  public String integrationParameterName() {
    return integrationParameterName;
  }

  public boolean matches(IntegrationResponseParameterExtension parameterExtension) {
    return Objects.equals(methodHeaderName, parameterExtension.methodHeaderName());
  }

  public IntegrationResponseParameterExtension update(IntegrationResponseParameterExtension parameterExtension) {
    return this.integrationParameterType(parameterExtension.integrationParameterType())
        .integrationParameterName(parameterExtension.integrationParameterName());
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(
        String.format("method.response.header.%s", methodHeaderName),
        String.format("integration.response.%s.%s", integrationParameterType.key(), integrationParameterName)
    );
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(methodHeaderName);
      Objects.requireNonNull(integrationParameterType);
      Objects.requireNonNull(integrationParameterType.key());
      Objects.requireNonNull(integrationParameterName);
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationResponseParameterExtension)) return false;
    IntegrationResponseParameterExtension that = (IntegrationResponseParameterExtension) object;
    return methodHeaderName.equals(that.methodHeaderName) &&
        integrationParameterType == that.integrationParameterType &&
        integrationParameterName.equals(that.integrationParameterName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(methodHeaderName, integrationParameterType, integrationParameterName);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationResponseParameterExtension.class.getSimpleName() + "[", "]")
        .add("methodHeaderName='" + methodHeaderName + "'")
        .add("integrationParameterType=" + integrationParameterType)
        .add("integrationParameterName='" + integrationParameterName + "'")
        .toString();
  }

  public static class Builder {
    @Nullable
    private String methodHeaderName;
    @Nullable
    private IntegrationResponseParameterType integrationParameterType;
    @Nullable
    private String integrationParameterName;

    Builder() {
    }

    public Builder methodHeaderName(@Nullable String methodHeaderName) {
      this.methodHeaderName = methodHeaderName;
      return this;
    }

    public Builder integrationParameterType(@Nullable IntegrationResponseParameterType integrationParameterType) {
      this.integrationParameterType = integrationParameterType;
      return this;
    }

    public Builder integrationParameterName(@Nullable String integrationParameterName) {
      this.integrationParameterName = integrationParameterName;
      return this;
    }

    public IntegrationResponseParameterExtension build() {
      return new IntegrationResponseParameterExtension(methodHeaderName, integrationParameterType, integrationParameterName);
    }
  }
}
