package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationIntegrationTypeExtension;
import io.swagger.v3.oas.models.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class IntegrationIntegrationTypeResolver implements IntegrationResolver<IntegrationIntegrationTypeExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationIntegrationTypeExtension resolve(Operation operation, HandlerMethod handlerMethod) {

    final IntegrationType integrationType = operation.findAnnotation(ApiGatewayIntegration.class)
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
