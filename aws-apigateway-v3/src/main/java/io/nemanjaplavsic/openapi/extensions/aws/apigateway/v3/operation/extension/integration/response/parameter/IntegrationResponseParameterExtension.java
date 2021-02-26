package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.parameter;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterSource;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ConvertableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ValidatableExtension;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationResponseParameterExtension implements ConvertableExtension<String>, ValidatableExtension {


  private final IntegrationResponseParameterSource source;
  private final String methodHeaderName;
  @Nullable
  private IntegrationResponseParameterType integrationParameterType;
  @Nullable
  private String integrationParameterName;
  @Nullable
  private String staticValue;

  public IntegrationResponseParameterExtension(IntegrationResponseParameterSource source, String methodHeaderName) {
    this.source = source;
    this.methodHeaderName = methodHeaderName;
  }

  public IntegrationResponseParameterExtension(IntegrationResponseParameterSource source, String methodHeaderName, @Nullable IntegrationResponseParameterType integrationParameterType, @Nullable String integrationParameterName, @Nullable String staticValue) {
    this.source = source;
    this.methodHeaderName = methodHeaderName;
    this.integrationParameterType = integrationParameterType;
    this.integrationParameterName = integrationParameterName;
    this.staticValue = staticValue;
  }

  public IntegrationResponseParameterExtension integrationParameterType(@Nullable IntegrationResponseParameterType integrationParameterType) {
    this.integrationParameterType = Objects.requireNonNullElse(integrationParameterType, IntegrationResponseParameterType.NONE);
    return this;
  }

  public IntegrationResponseParameterExtension integrationParameterName(@Nullable String integrationParameterName) {
    this.integrationParameterName = integrationParameterName;
    return this;
  }

  public IntegrationResponseParameterExtension staticValue(@Nullable String staticValue) {
    this.staticValue = staticValue;
    return this;
  }

  public IntegrationResponseParameterSource getSource() {
    return source;
  }

  public String getMethodHeaderName() {
    return methodHeaderName;
  }

  @Nullable
  public IntegrationResponseParameterType getIntegrationParameterType() {
    return integrationParameterType;
  }

  @Nullable
  public String getIntegrationParameterName() {
    return integrationParameterName;
  }

  @Nullable
  public String getStaticValue() {
    return staticValue;
  }

  public boolean matches(IntegrationResponseParameterExtension parameterExtension) {
    return Objects.equals(methodHeaderName, parameterExtension.getMethodHeaderName());
  }

  public String getExtensionKey() {
    return String.format("method.response.header.%s", methodHeaderName);
  }

  public String getExtensionValue() {
    if (IntegrationResponseParameterSource.INTEGRATION.equals(source)) {
      return String.format("integration.response.%s.%s", integrationParameterType.key(), integrationParameterName);
    } else {
      return String.format("%s", staticValue);
    }
  }

  public boolean isValid() {
    try {
      Objects.requireNonNull(source);
      Objects.requireNonNull(methodHeaderName);
      if (IntegrationResponseParameterSource.INTEGRATION.equals(source)) {
        Objects.requireNonNull(integrationParameterType);
        Objects.requireNonNull(integrationParameterType.key());
        Objects.requireNonNull(integrationParameterName);
      } else {
        Objects.requireNonNull(staticValue);
      }
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
    return source == that.source &&
        Objects.equals(methodHeaderName, that.methodHeaderName) &&
        integrationParameterType == that.integrationParameterType &&
        Objects.equals(integrationParameterName, that.integrationParameterName) &&
        Objects.equals(staticValue, that.staticValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, methodHeaderName, integrationParameterType, integrationParameterName, staticValue);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationResponseParameterExtension.class.getSimpleName() + "[", "]")
        .add("source=" + source)
        .add("methodHeaderName='" + methodHeaderName + "'")
        .add("integrationParameterType=" + integrationParameterType)
        .add("integrationParameterName='" + integrationParameterName + "'")
        .add("staticValue='" + staticValue + "'")
        .toString();
  }
}
