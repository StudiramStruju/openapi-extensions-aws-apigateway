package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationContentHandlingExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.web.method.HandlerMethod;

import java.util.Objects;
import java.util.Optional;

public class IntegrationContentHandlingResolver implements IntegrationResolver<IntegrationContentHandlingExtension> {

  private final ApiGatewayServiceProperties properties;

  public IntegrationContentHandlingResolver(ApiGatewayServiceProperties properties) {
    this.properties = properties;
  }

  @Override
  public IntegrationContentHandlingExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    final ContentHandling contentHandling = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::contentHandling)
        .orElse(null);

    final ContentHandling contentHandlingProperties = properties.getContentHandling();

    if (Objects.nonNull(contentHandling) && Objects.nonNull(contentHandling.key())) {
      return new IntegrationContentHandlingExtension(contentHandling);
    } else if (Objects.nonNull(contentHandlingProperties) && Objects.nonNull(contentHandlingProperties.key())) {
      return new IntegrationContentHandlingExtension(contentHandlingProperties);
    }

    return null;
  }
}
