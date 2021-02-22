package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationCacheNamespaceExtension;
import io.swagger.v3.oas.models.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IntegrationCacheNamespaceResolver implements IntegrationResolver<IntegrationCacheNamespaceExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationCacheNamespaceExtension resolve(Operation operation, HandlerMethod handlerMethod) {

    final String cacheNamespace = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::cacheNamespace)
        .orElse(null);

    final String cacheNamespaceProperties = properties.getCacheNamespace();

    if (StringUtils.hasText(cacheNamespace)) {
      return new IntegrationCacheNamespaceExtension(cacheNamespace);
    } else if (StringUtils.hasText(cacheNamespaceProperties)) {
      return new IntegrationCacheNamespaceExtension(cacheNamespaceProperties);
    }
    return null;
  }
}
