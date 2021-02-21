package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension;

import springfox.documentation.service.SecurityScheme;

public interface ApiGatewaySecurityDefinitionExtension<T extends SecurityScheme> {

  T toSecurityScheme();
}
