package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.security;

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
    this.addExtension(extension.getExtensionKey(), extension.getExtensionValue());
    return this;
  }


}
