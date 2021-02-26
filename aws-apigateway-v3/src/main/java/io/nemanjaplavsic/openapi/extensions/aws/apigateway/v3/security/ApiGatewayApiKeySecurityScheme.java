package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security;


import io.swagger.v3.oas.models.security.SecurityScheme;

public class ApiGatewayApiKeySecurityScheme extends SecurityScheme {
  public ApiGatewayApiKeySecurityScheme() {
    super();
    this.name("x-api-key")
        .in(In.HEADER)
        .type(Type.APIKEY);
  }
}
