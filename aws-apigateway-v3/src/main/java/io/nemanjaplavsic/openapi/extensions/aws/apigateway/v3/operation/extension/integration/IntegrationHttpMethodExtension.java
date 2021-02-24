package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationHttpMethodExtension implements IntegrationExtension<String> {

  public static final String NAME = "httpMethod";

  @Nullable
  private RequestMethod requestMethod;
  @Nullable
  private HttpMethod httpMethod;


  public IntegrationHttpMethodExtension(@Nullable RequestMethod requestMethod) {
    this(requestMethod, null);
  }

  public IntegrationHttpMethodExtension(@Nullable RequestMethod requestMethod, @Nullable HttpMethod httpMethod) {
    this.requestMethod = requestMethod;
    this.httpMethod = Objects.requireNonNullElse(httpMethod, HttpMethod.RESOLVE_FROM_METHOD);
  }

  public IntegrationHttpMethodExtension httpMethod(@Nullable HttpMethod httpMethod) {
    this.httpMethod = Objects.requireNonNullElse(httpMethod, HttpMethod.RESOLVE_FROM_METHOD);
    return this;
  }

  public IntegrationHttpMethodExtension requestMethod(RequestMethod requestMethod) {
    this.requestMethod = Objects.requireNonNull(requestMethod, "RequestMethod cannot be null!");
    return this;
  }

  @Nullable
  public HttpMethod httpMethod() {
    return httpMethod;
  }

  @Nullable
  public RequestMethod requestMethod() {
    return requestMethod;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  public String getExtensionValue() {
    if (HttpMethod.RESOLVE_FROM_METHOD.equals(httpMethod)) {
      return requestMethod.name();
    }
    return httpMethod.key();
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(httpMethod);
      if (HttpMethod.RESOLVE_FROM_METHOD.equals(httpMethod)) {
        Objects.requireNonNull(requestMethod);
      }
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationHttpMethodExtension)) return false;
    IntegrationHttpMethodExtension that = (IntegrationHttpMethodExtension) object;
    return httpMethod == that.httpMethod;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationHttpMethodExtension.class.getSimpleName() + "[", "]")
        .add("HttpMethod=" + httpMethod)
        .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(httpMethod);
  }
}
