package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration.IntegrationCacheKeyParametersExtension;
import io.swagger.v3.oas.models.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(ApiGatewayServiceProperties.class)
public class IntegrationCacheKeyParametersResolver implements IntegrationResolver<IntegrationCacheKeyParametersExtension> {

  private final ApiGatewayServiceProperties properties;

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
