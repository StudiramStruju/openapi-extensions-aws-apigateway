package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationTimeoutInMillisExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.web.method.HandlerMethod;

import java.util.Objects;
import java.util.Optional;

public class IntegrationTimeoutInMillisResolver implements IntegrationResolver<IntegrationTimeoutInMillisExtension> {

  private final ApiGatewayServiceProperties properties;

  public IntegrationTimeoutInMillisResolver(ApiGatewayServiceProperties properties) {
    this.properties = properties;
  }

  @Override
  public IntegrationTimeoutInMillisExtension resolve(Operation operation, HandlerMethod handlerMethod) {

    final Integer timeoutInMillis = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::timeoutInMillis)
        .orElse(null);

    final Integer timeoutInMillisProperties = properties.getTimeoutInMillis();

    if (Objects.nonNull(timeoutInMillis) && timeoutInMillis != -1) {
      return new IntegrationTimeoutInMillisExtension(timeoutInMillis);
    } else if (Objects.nonNull(timeoutInMillisProperties)) {
      return new IntegrationTimeoutInMillisExtension(timeoutInMillisProperties);
    }
    return new IntegrationTimeoutInMillisExtension(null);
  }
}
