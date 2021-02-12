package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.ApiGatewayIntegrationExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.IntegrationCacheKeyParametersResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.IntegrationRequestParametersResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.IntegrationResponsesResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.IntegrationUriExtensionResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

@Component
@RequiredArgsConstructor
public class ApiGatewayIntegrationExtensionResolver implements OperationBuilderExtensionResolver<ApiGatewayIntegrationExtension> {

  private final IntegrationCacheKeyParametersResolver cacheKeyParametersResolver;
  private final IntegrationUriExtensionResolver uriExtensionResolver;
  private final IntegrationRequestParametersResolver requestParametersResolver;
  private final IntegrationResponsesResolver responsesResolver;

  @Override
  public ApiGatewayIntegrationExtension resolve(OperationContext context) {
    return ApiGatewayIntegrationExtension.builder()
        .cacheKeyParameters(cacheKeyParametersResolver.resolve(context))
        .uri(uriExtensionResolver.resolve(context))
        .requestParameters(requestParametersResolver.resolve(context))
        .responses(responsesResolver.resolve(context))
        .build();
  }
}
