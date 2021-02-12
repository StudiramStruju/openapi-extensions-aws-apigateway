package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationHttpMethodExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "httpMethod";

  private HttpMethod httpMethod;
  private RequestMethod requestMethod;

  public IntegrationHttpMethodExtension() {
  }

  public IntegrationHttpMethodExtension(@Nullable HttpMethod httpMethod, RequestMethod requestMethod) {
    this.httpMethod = Objects.requireNonNullElse(httpMethod, HttpMethod.RESOLVE_FROM_METHOD);
    this.requestMethod = requestMethod;
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationHttpMethodExtension httpMethod(HttpMethod httpMethod) {
    this.httpMethod = Objects.requireNonNullElse(httpMethod, HttpMethod.RESOLVE_FROM_METHOD);
    return this;
  }

  public IntegrationHttpMethodExtension requestMethod(RequestMethod requestMethod) {
    this.requestMethod = requestMethod;
    return this;
  }

  public HttpMethod httpMethod() {
    return httpMethod;
  }

  public RequestMethod requestMethod() {
    return requestMethod;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    if (HttpMethod.RESOLVE_FROM_METHOD.equals(httpMethod)) {
      return new StringVendorExtension(NAME, requestMethod.name());
    }
    return new StringVendorExtension(NAME, httpMethod.key());
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

  public static class Builder {
    private HttpMethod httpMethod;
    private RequestMethod requestMethod;

    Builder() {
      httpMethod = HttpMethod.RESOLVE_FROM_METHOD;
    }

    public IntegrationHttpMethodExtension.Builder httpMethod(HttpMethod httpMethod) {
      this.httpMethod = httpMethod;
      return this;
    }

    public Builder requestMethod(RequestMethod requestMethod) {
      this.requestMethod = requestMethod;
      return this;
    }

    public IntegrationHttpMethodExtension build() {
      return new IntegrationHttpMethodExtension(httpMethod, requestMethod);
    }
  }
}
