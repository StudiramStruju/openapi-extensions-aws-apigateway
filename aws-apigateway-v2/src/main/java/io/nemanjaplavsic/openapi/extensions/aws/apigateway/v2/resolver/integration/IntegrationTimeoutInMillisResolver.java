package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationTimeoutInMillisExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class IntegrationTimeoutInMillisResolver implements IntegrationResolver<IntegrationTimeoutInMillisExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationTimeoutInMillisExtension resolve(OperationContext context) {

    final Integer timeoutInMillis = context.findAnnotation(ApiGatewayIntegration.class)
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
