package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationContentHandlingExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class IntegrationContentHandlingResolver implements IntegrationResolver<IntegrationContentHandlingExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationContentHandlingExtension resolve(OperationContext context) {
    final ContentHandling contentHandling = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::contentHandling)
        .orNull();

    final ContentHandling contentHandlingProperties = properties.getContentHandling();

    if (Objects.nonNull(contentHandling) && Objects.nonNull(contentHandling.key())) {
      return new IntegrationContentHandlingExtension(contentHandling);
    } else if (Objects.nonNull(contentHandlingProperties) && Objects.nonNull(contentHandlingProperties.key())) {
      return new IntegrationContentHandlingExtension(contentHandlingProperties);
    }

    return null;
  }
}
