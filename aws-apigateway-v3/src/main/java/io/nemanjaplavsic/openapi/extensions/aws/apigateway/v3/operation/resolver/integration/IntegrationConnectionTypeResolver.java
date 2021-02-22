package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ConnectionType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationConnectionTypeExtension;
import io.swagger.v3.oas.models.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IntegrationConnectionTypeResolver implements IntegrationResolver<IntegrationConnectionTypeExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationConnectionTypeExtension resolve(Operation operation, HandlerMethod handlerMethod) {

    final ConnectionType connectionType = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::connectionType)
        .orElse(null);

    final ConnectionType connectionTypeProperties = properties.getConnectionType();

    if (Objects.nonNull(connectionType) && Objects.nonNull(connectionType.key())) {
      return new IntegrationConnectionTypeExtension(connectionType);
    } else if (Objects.nonNull(connectionTypeProperties) && Objects.nonNull(connectionTypeProperties.key())) {
      return new IntegrationConnectionTypeExtension(properties.getConnectionType());
    }

    return null;
  }
}
