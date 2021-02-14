package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationCacheNamespaceExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.service.contexts.OperationContext;

@Component
@RequiredArgsConstructor
public class IntegrationCacheNamespaceResolver implements IntegrationResolver<IntegrationCacheNamespaceExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationCacheNamespaceExtension resolve(OperationContext context) {

    final String cacheNamespace = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::cacheNamespace)
        .orNull();

    final String cacheNamespaceProperties = properties.getCacheNamespace();

    if (StringUtils.hasText(cacheNamespace)) {
      return new IntegrationCacheNamespaceExtension(cacheNamespace);
    } else if (StringUtils.hasText(cacheNamespaceProperties)) {
      return new IntegrationCacheNamespaceExtension(cacheNamespaceProperties);
    }
    return null;
  }
}
