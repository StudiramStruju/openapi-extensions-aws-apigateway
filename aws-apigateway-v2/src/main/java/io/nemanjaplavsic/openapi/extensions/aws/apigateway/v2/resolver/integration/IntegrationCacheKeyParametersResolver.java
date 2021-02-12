package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationCacheKeyParametersExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(ApiGatewayServiceProperties.class)
public class IntegrationCacheKeyParametersResolver implements IntegrationResolver<IntegrationCacheKeyParametersExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationCacheKeyParametersExtension resolve(OperationContext context) {
    IntegrationCacheKeyParametersExtension extension = new IntegrationCacheKeyParametersExtension(properties.getCacheKeyParameters());

    final Set<String> cacheKeyParameters = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::cacheKeyParameters)
        .transform(value -> Set.copyOf(Arrays.asList(value)))
        .or(new HashSet<>());

    extension.cacheKeyParameter(cacheKeyParameters);

    return extension;
  }


}
