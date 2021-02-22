package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.PassThroughBehavior;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationPassThroughBehaviorExtension;
import io.swagger.v3.oas.models.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IntegrationPassTroughBehaviourResolver implements IntegrationResolver<IntegrationPassThroughBehaviorExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationPassThroughBehaviorExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    final PassThroughBehavior passThroughBehavior = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::passthroughBehavior)
        .orElse(null);

    final PassThroughBehavior passThroughBehaviorProperties = properties.getPassthroughBehavior();

    if (Objects.nonNull(passThroughBehavior) && Objects.nonNull(passThroughBehavior.key())) {
      return new IntegrationPassThroughBehaviorExtension(passThroughBehavior);
    } else if (Objects.nonNull(passThroughBehaviorProperties) && Objects.nonNull(passThroughBehaviorProperties.key())) {
      return new IntegrationPassThroughBehaviorExtension(passThroughBehaviorProperties);
    }

    return null;
  }
}
