package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ConnectionType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationConnectionTypeExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class IntegrationConnectionTypeResolver implements IntegrationResolver<IntegrationConnectionTypeExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationConnectionTypeExtension resolve(OperationContext context) {

    final ConnectionType connectionType = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::connectionType)
        .orNull();

    final ConnectionType connectionTypeProperties = properties.getConnectionType();

    if (Objects.nonNull(connectionType) && Objects.nonNull(connectionType.key())) {
      return new IntegrationConnectionTypeExtension(connectionType);
    } else if (Objects.nonNull(connectionTypeProperties) && Objects.nonNull(connectionTypeProperties.key())) {
      return new IntegrationConnectionTypeExtension(properties.getConnectionType());
    }

    return null;
  }
}
