package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration;

import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationTimeoutInMillisExtension implements ApiGatewayIntegrationExtension<Integer> {

  public static final String NAME = "timeoutInMillis";
  public static final int DEFAULT = 29000;

  private Integer timeoutInMillis;

  public IntegrationTimeoutInMillisExtension() {
    this(null);
  }

  public IntegrationTimeoutInMillisExtension(@Nullable Integer timeoutInMillis) {
    this.timeoutInMillis = Objects.requireNonNullElse(timeoutInMillis, DEFAULT);
  }

  public IntegrationTimeoutInMillisExtension timeoutInMillis(@Nullable Integer timeoutInMillis) {
    this.timeoutInMillis = Objects.requireNonNullElse(timeoutInMillis, DEFAULT);
    return this;
  }

  public Integer getTimeoutInMillis() {
    return timeoutInMillis;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  public Integer getExtensionValue() {
    return timeoutInMillis;
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
}
