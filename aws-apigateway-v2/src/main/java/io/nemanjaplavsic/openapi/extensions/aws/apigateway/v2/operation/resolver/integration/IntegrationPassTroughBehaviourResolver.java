package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.PassThroughBehavior;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationPassThroughBehaviorExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class IntegrationPassTroughBehaviourResolver implements IntegrationResolver<IntegrationPassThroughBehaviorExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationPassThroughBehaviorExtension resolve(OperationContext context) {
    final PassThroughBehavior passThroughBehavior = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::passthroughBehavior)
        .orNull();

    final PassThroughBehavior passThroughBehaviorProperties = properties.getPassThroughBehavior();

    if (Objects.nonNull(passThroughBehavior) && Objects.nonNull(passThroughBehavior.key())) {
      return new IntegrationPassThroughBehaviorExtension(passThroughBehavior);
    } else if (Objects.nonNull(passThroughBehaviorProperties) && Objects.nonNull(passThroughBehaviorProperties.key())) {
      return new IntegrationPassThroughBehaviorExtension(passThroughBehaviorProperties);
    }

    return null;
  }
}
