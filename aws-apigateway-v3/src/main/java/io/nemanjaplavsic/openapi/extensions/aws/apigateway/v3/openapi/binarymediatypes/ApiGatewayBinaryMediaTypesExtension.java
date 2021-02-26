package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.openapi.binarymediatypes;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.openapi.OpenApiGatewayExtension;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public class ApiGatewayBinaryMediaTypesExtension implements OpenApiGatewayExtension<Set<String>> {

  public static final String NAME = "x-amazon-apigateway-binary-media-types";

  private final Set<String> types;

  public ApiGatewayBinaryMediaTypesExtension() {
    this(null);
  }

  public ApiGatewayBinaryMediaTypesExtension(Set<String> types) {
    this.types = Objects.requireNonNullElse(types, new HashSet<>());
  }

  public Set<String> getTypes() {
    return types;
  }

  public ApiGatewayBinaryMediaTypesExtension type(String type) {
    this.types.add(type);
    return this;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  @Override
  public Set<String> getExtensionValue() {
    return getTypes();
  }

  @Override
  public boolean isValid() {
    return !types.isEmpty();
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof ApiGatewayBinaryMediaTypesExtension)) return false;
    ApiGatewayBinaryMediaTypesExtension that = (ApiGatewayBinaryMediaTypesExtension) object;
    return Objects.equals(getTypes(), that.getTypes());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTypes());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ApiGatewayBinaryMediaTypesExtension.class.getSimpleName() + "[", "]")
        .add("types=" + types)
        .toString();
  }
}
