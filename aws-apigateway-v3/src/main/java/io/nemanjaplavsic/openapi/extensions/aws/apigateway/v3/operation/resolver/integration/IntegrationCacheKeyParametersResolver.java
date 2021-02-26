package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationCacheKeyParametersExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class IntegrationCacheKeyParametersResolver implements IntegrationResolver<IntegrationCacheKeyParametersExtension> {

  private final ApiGatewayServiceProperties properties;

  public IntegrationCacheKeyParametersResolver(ApiGatewayServiceProperties properties) {
    this.properties = properties;
  }

  @Override
  public IntegrationCacheKeyParametersExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    IntegrationCacheKeyParametersExtension extension = new IntegrationCacheKeyParametersExtension(properties.getCacheKeyParameters());

    final Set<String> cacheKeyParameters = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::cacheKeyParameters)
        .map(value -> Set.copyOf(Arrays.asList(value)))
        .orElse(new HashSet<>());

    return extension.cacheKeyParameter(cacheKeyParameters);
  }

}
