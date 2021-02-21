package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.security;

import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class ApiGatewayAuthTypeExtension implements ApiGatewaySecurityExtension<StringVendorExtension> {

  public static final String NAME = "x-amazon-apigateway-authtype";

  private final String typeName;

  public ApiGatewayAuthTypeExtension(String typeName) {
    this.typeName = Objects.requireNonNull(typeName, "ApiGatewayAuthTypeExtension typeName should not be null!");
  }

  public static ApiGatewayAuthTypeExtension cognitoUserPools() {
    return new ApiGatewayAuthTypeExtension("cognito_user_pools");
  }

  public static ApiGatewayAuthTypeExtension awsSigV4() {
    return new ApiGatewayAuthTypeExtension("awsSigv4");
  }

  public String typeName() {
    return typeName;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, typeName);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof ApiGatewayAuthTypeExtension)) return false;
    ApiGatewayAuthTypeExtension that = (ApiGatewayAuthTypeExtension) object;
    return Objects.equals(typeName, that.typeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeName);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ApiGatewayAuthTypeExtension.class.getSimpleName() + "[", "]")
        .add("typeName='" + typeName + "'")
        .toString();
  }
}
