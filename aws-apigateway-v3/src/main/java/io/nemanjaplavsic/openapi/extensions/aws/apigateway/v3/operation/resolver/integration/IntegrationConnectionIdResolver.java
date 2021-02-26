package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationConnectionIdExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import java.util.Optional;

public class IntegrationConnectionIdResolver implements IntegrationResolver<IntegrationConnectionIdExtension> {

  private final ApiGatewayServiceProperties properties;

  public IntegrationConnectionIdResolver(ApiGatewayServiceProperties properties) {
    this.properties = properties;
  }

  @Override
  public IntegrationConnectionIdExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    final String connectionId = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::connectionId)
        .orElse(null);

    final String connectionIdProperties = properties.getConnectionId();

    if (StringUtils.hasText(connectionId)) {
      return new IntegrationConnectionIdExtension(connectionId);
    } else if (StringUtils.hasText(connectionIdProperties)) {
      return new IntegrationConnectionIdExtension(connectionIdProperties);
    }
    return null;
  }
}
