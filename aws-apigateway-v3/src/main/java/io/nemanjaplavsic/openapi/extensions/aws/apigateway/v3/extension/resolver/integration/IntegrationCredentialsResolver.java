package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration.IntegrationCredentialsExtension;
import io.swagger.v3.oas.models.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IntegrationCredentialsResolver implements IntegrationResolver<IntegrationCredentialsExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationCredentialsExtension resolve(Operation operation, HandlerMethod handlerMethod) {

    final String credentials = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::credentials)
        .orElse(null);

    final String credentialsProperties = properties.getCredentials();

    if (StringUtils.hasText(credentials)) {
      return new IntegrationCredentialsExtension(credentials);
    } else if (StringUtils.hasText(credentialsProperties)) {
      return new IntegrationCredentialsExtension(credentialsProperties);
    }
    return null;
  }
}
