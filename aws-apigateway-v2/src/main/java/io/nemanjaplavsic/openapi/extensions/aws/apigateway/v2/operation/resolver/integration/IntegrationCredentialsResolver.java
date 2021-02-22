package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationCredentialsExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.service.contexts.OperationContext;

@Component
@RequiredArgsConstructor
public class IntegrationCredentialsResolver implements IntegrationResolver<IntegrationCredentialsExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationCredentialsExtension resolve(OperationContext context) {

    final String credentials = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::credentials)
        .orNull();

    final String credentialsProperties = properties.getCredentials();

    if (StringUtils.hasText(credentials)) {
      return new IntegrationCredentialsExtension(credentials);
    } else if (StringUtils.hasText(credentialsProperties)) {
      return new IntegrationCredentialsExtension(credentialsProperties);
    }
    return null;
  }
}
