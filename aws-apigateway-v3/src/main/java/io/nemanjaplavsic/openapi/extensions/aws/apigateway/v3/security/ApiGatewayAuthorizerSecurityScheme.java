package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security.extension.ApiGatewayAuthTypeExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security.extension.ApiGatewayAuthorizerExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security.extension.ApiGatewaySecurityExtension;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class ApiGatewayAuthorizerSecurityScheme extends SecurityScheme {

  public ApiGatewayAuthorizerSecurityScheme() {
    super();
  }

  @Override
  public ApiGatewayAuthorizerSecurityScheme in(In in) {
    super.in(in);
    return this;
  }

  @Override
  public ApiGatewayAuthorizerSecurityScheme name(String name) {
    super.name(name);
    return this;
  }

  @Override
  public ApiGatewayAuthorizerSecurityScheme description(String description) {
    super.description(description);
    return this;
  }

  public ApiGatewayAuthorizerSecurityScheme authType(ApiGatewayAuthTypeExtension authType) {
    this.extension(authType);
    return this;
  }

  public ApiGatewayAuthorizerSecurityScheme authorizer(ApiGatewayAuthorizerExtension authorizer) {
    this.extension(authorizer);
    return this;
  }

  public ApiGatewayAuthorizerSecurityScheme extension(ApiGatewaySecurityExtension<?> extension) {
    this.extension(extension.getExtensionKey(), extension.getExtensionValue());
    return this;
  }

  public ApiGatewayAuthorizerSecurityScheme extension(String name, Object value) {
    this.addExtension(name, value);
    return this;
  }


}
