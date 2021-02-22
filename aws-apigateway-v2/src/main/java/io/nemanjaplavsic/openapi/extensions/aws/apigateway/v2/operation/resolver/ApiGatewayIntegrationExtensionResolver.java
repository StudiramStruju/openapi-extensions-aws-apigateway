package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.ApiGatewayIntegrationExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationCacheKeyParametersResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationCacheNamespaceResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationConnectionIdResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationConnectionTypeResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationCredentialsResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationHttpMethodResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationIntegrationTypeResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationPassTroughBehaviourResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationRequestParametersResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationResponsesResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationTimeoutInMillisResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration.IntegrationUriExtensionResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

@Component
@RequiredArgsConstructor
public class ApiGatewayIntegrationExtensionResolver implements OperationBuilderExtensionResolver<ApiGatewayIntegrationExtension> {

  private final IntegrationCacheKeyParametersResolver cacheKeyParametersResolver;
  private final IntegrationCacheNamespaceResolver cacheNamespaceResolver;
  private final IntegrationConnectionIdResolver connectionIdResolver;
  private final IntegrationConnectionTypeResolver connectionTypeResolver;
  private final IntegrationCredentialsResolver credentialsResolver;
  private final IntegrationHttpMethodResolver httpMethodResolver;
  private final IntegrationIntegrationTypeResolver integrationIntegrationTypeResolver;
  private final IntegrationPassTroughBehaviourResolver integrationPassTroughBehaviourResolver;
  private final IntegrationTimeoutInMillisResolver integrationTimeoutInMillisResolver;
  private final IntegrationUriExtensionResolver uriExtensionResolver;
  private final IntegrationRequestParametersResolver requestParametersResolver;
  private final IntegrationResponsesResolver responsesResolver;

  @Override
  public ApiGatewayIntegrationExtension resolve(OperationContext context) {
    return new ApiGatewayIntegrationExtension()
        .cacheKeyParameters(cacheKeyParametersResolver.resolve(context))
        .cacheNamespace(cacheNamespaceResolver.resolve(context))
        .connectionId(connectionIdResolver.resolve(context))
        .connectionType(connectionTypeResolver.resolve(context))
        .credentials(credentialsResolver.resolve(context))
        .httpMethod(httpMethodResolver.resolve(context))
        .type(integrationIntegrationTypeResolver.resolve(context))
        .passthroughBehavior(integrationPassTroughBehaviourResolver.resolve(context))
        .timeoutInMillis(integrationTimeoutInMillisResolver.resolve(context))
        .uri(uriExtensionResolver.resolve(context))
        .requestParameters(requestParametersResolver.resolve(context))
        .responses(responsesResolver.resolve(context));
  }
}
