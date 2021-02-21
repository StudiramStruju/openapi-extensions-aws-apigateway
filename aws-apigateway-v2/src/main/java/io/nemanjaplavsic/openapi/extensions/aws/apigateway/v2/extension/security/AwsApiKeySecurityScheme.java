package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.security;

import springfox.documentation.service.ApiKey;

public class AwsApiKeySecurityScheme extends ApiKey {
  public AwsApiKeySecurityScheme() {
    super("AwsApiKey", "x-api-key", "header");
  }
}
