package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationTimeoutInMillisExtension;
import io.swagger.v3.oas.models.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class IntegrationTimeoutInMillisResolver implements IntegrationResolver<IntegrationTimeoutInMillisExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationTimeoutInMillisExtension resolve(Operation operation, HandlerMethod handlerMethod) {

    final Integer timeoutInMillis = operation.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::timeoutInMillis)
        .orNull();

    final Integer timeoutInMillisProperties = properties.getTimeoutInMillis();

    if (Objects.nonNull(timeoutInMillis) && timeoutInMillis != -1) {
      return new IntegrationTimeoutInMillisExtension(timeoutInMillis);
    } else if (Objects.nonNull(timeoutInMillisProperties)) {
      return new IntegrationTimeoutInMillisExtension(timeoutInMillisProperties);
    }
    return new IntegrationTimeoutInMillisExtension(null);
  }
}
