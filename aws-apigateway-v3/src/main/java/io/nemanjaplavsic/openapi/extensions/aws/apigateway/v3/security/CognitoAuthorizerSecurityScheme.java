package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security.extension.ApiGatewayAuthTypeExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security.extension.ApiGatewayAuthorizerExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security.extension.ApiGatewaySecurityExtension;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class CognitoAuthorizerSecurityScheme extends SecurityScheme {

  public CognitoAuthorizerSecurityScheme() {
    super();
  }

  @Override
  public CognitoAuthorizerSecurityScheme in(In in) {
    super.in(in);
    return this;
  }

  @Override
  public CognitoAuthorizerSecurityScheme name(String name) {
    super.name(name);
    return this;
  }

  @Override
  public CognitoAuthorizerSecurityScheme description(String description) {
    super.description(description);
    return this;
  }

  public CognitoAuthorizerSecurityScheme authType(ApiGatewayAuthTypeExtension authType) {
    this.extension(authType);
    return this;
  }

  public CognitoAuthorizerSecurityScheme authorizer(ApiGatewayAuthorizerExtension authorizer) {
    this.extension(authorizer);
    return this;
  }

  public CognitoAuthorizerSecurityScheme extension(ApiGatewaySecurityExtension<?> extension) {
    this.extension(extension.getExtensionKey(), extension.getExtensionValue());
    return this;
  }

  public CognitoAuthorizerSecurityScheme extension(String name, Object value) {
    this.addExtension(name, value);
    return this;
  }


}
