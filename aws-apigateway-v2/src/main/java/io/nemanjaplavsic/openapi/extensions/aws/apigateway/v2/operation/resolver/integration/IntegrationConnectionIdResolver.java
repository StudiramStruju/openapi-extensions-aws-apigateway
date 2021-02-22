package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationConnectionIdExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.service.contexts.OperationContext;

@Component
@RequiredArgsConstructor
public class IntegrationConnectionIdResolver implements IntegrationResolver<IntegrationConnectionIdExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationConnectionIdExtension resolve(OperationContext context) {
    final String connectionId = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::connectionId)
        .orNull();

    final String connectionIdProperties = properties.getConnectionId();

    if (StringUtils.hasText(connectionId)) {
      return new IntegrationConnectionIdExtension(connectionId);
    } else if (StringUtils.hasText(connectionIdProperties)) {
      return new IntegrationConnectionIdExtension(connectionIdProperties);
    }
    return null;
  }
}
