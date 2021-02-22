package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security;


import io.swagger.v3.oas.models.security.SecurityScheme;

public class AwsApiKeySecurityScheme extends SecurityScheme {
  public AwsApiKeySecurityScheme() {
    super();
    this.name("x-api-key")
        .in(In.HEADER)
        .type(Type.APIKEY);
  }
}
