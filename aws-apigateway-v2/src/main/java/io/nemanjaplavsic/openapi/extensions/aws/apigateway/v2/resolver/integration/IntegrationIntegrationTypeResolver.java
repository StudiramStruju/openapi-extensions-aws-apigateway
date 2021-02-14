package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationIntegrationTypeExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class IntegrationIntegrationTypeResolver implements IntegrationResolver<IntegrationIntegrationTypeExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationIntegrationTypeExtension resolve(OperationContext context) {

    final IntegrationType integrationType = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::type)
        .orNull();

    final IntegrationType integrationTypeProperties = properties.getIntegrationType();

    if (Objects.nonNull(integrationType) && Objects.nonNull(integrationType.key())) {
      return new IntegrationIntegrationTypeExtension(integrationType);
    } else if (Objects.nonNull(integrationTypeProperties) && Objects.nonNull(integrationTypeProperties.key())) {
      return new IntegrationIntegrationTypeExtension(integrationTypeProperties);
    }

    return null;
  }
}
