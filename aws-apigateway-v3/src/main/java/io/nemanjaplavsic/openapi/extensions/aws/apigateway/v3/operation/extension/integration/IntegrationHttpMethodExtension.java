package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public class IntegrationHttpMethodExtension implements ApiGatewayIntegrationExtension<String> {

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

  public IntegrationHttpMethodExtension requestMethod(@Nullable RequestMethod requestMethod) {
    this.requestMethod = requestMethod;
    return this;
  }

  @Nullable
  public HttpMethod getHttpMethod() {
    return httpMethod;
  }

  @Nullable
  public RequestMethod getRequestMethod() {
    return requestMethod;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  @Nullable
  public String getExtensionValue() {
    if (HttpMethod.RESOLVE_FROM_METHOD.equals(httpMethod)) {
      return Optional.ofNullable(requestMethod).map(RequestMethod::name).orElse(null);
    }
    return Optional.ofNullable(httpMethod).map(HttpMethod::key).orElse(null);
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
