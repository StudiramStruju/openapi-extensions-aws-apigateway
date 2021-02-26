package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.ApiGatewayIntegrationExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationCacheKeyParametersResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationCacheNamespaceResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationConnectionIdResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationConnectionTypeResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationCredentialsResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationHttpMethodResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationIntegrationTypeResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationPassTroughBehaviourResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationRequestParametersResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationRequestTemplatesResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationResponsesResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationTimeoutInMillisResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration.IntegrationUriExtensionResolver;
import io.swagger.v3.oas.models.Operation;
import org.springframework.web.method.HandlerMethod;

public class ApiGatewayIntegrationExtensionResolver implements OperationAwsExtensionResolver<ApiGatewayIntegrationExtension> {

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
  private final IntegrationRequestTemplatesResolver requestTemplatesResolver;
  private final IntegrationResponsesResolver responsesResolver;


  public ApiGatewayIntegrationExtensionResolver(IntegrationCacheKeyParametersResolver cacheKeyParametersResolver,
                                                IntegrationCacheNamespaceResolver cacheNamespaceResolver,
                                                IntegrationConnectionIdResolver connectionIdResolver,
                                                IntegrationConnectionTypeResolver connectionTypeResolver,
                                                IntegrationCredentialsResolver credentialsResolver,
                                                IntegrationHttpMethodResolver httpMethodResolver,
                                                IntegrationIntegrationTypeResolver integrationIntegrationTypeResolver,
                                                IntegrationPassTroughBehaviourResolver integrationPassTroughBehaviourResolver,
                                                IntegrationTimeoutInMillisResolver integrationTimeoutInMillisResolver,
                                                IntegrationUriExtensionResolver uriExtensionResolver,
                                                IntegrationRequestParametersResolver requestParametersResolver,
                                                IntegrationRequestTemplatesResolver requestTemplatesResolver,
                                                IntegrationResponsesResolver responsesResolver) {
    this.cacheKeyParametersResolver = cacheKeyParametersResolver;
    this.cacheNamespaceResolver = cacheNamespaceResolver;
    this.connectionIdResolver = connectionIdResolver;
    this.connectionTypeResolver = connectionTypeResolver;
    this.credentialsResolver = credentialsResolver;
    this.httpMethodResolver = httpMethodResolver;
    this.integrationIntegrationTypeResolver = integrationIntegrationTypeResolver;
    this.integrationPassTroughBehaviourResolver = integrationPassTroughBehaviourResolver;
    this.integrationTimeoutInMillisResolver = integrationTimeoutInMillisResolver;
    this.uriExtensionResolver = uriExtensionResolver;
    this.requestParametersResolver = requestParametersResolver;
    this.requestTemplatesResolver = requestTemplatesResolver;
    this.responsesResolver = responsesResolver;
  }

  @Override
  public ApiGatewayIntegrationExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    return new ApiGatewayIntegrationExtension()
        .cacheKeyParameters(cacheKeyParametersResolver.resolve(operation, handlerMethod))
        .cacheNamespace(cacheNamespaceResolver.resolve(operation, handlerMethod))
        .connectionId(connectionIdResolver.resolve(operation, handlerMethod))
        .connectionType(connectionTypeResolver.resolve(operation, handlerMethod))
        .credentials(credentialsResolver.resolve(operation, handlerMethod))
        .httpMethod(httpMethodResolver.resolve(operation, handlerMethod))
        .type(integrationIntegrationTypeResolver.resolve(operation, handlerMethod))
        .passthroughBehavior(integrationPassTroughBehaviourResolver.resolve(operation, handlerMethod))
        .timeoutInMillis(integrationTimeoutInMillisResolver.resolve(operation, handlerMethod))
        .uri(uriExtensionResolver.resolve(operation, handlerMethod))
        .requestParameters(requestParametersResolver.resolve(operation, handlerMethod))
        .requestTemplates(requestTemplatesResolver.resolve(operation, handlerMethod))
        .responses(responsesResolver.resolve(operation, handlerMethod));
  }
}
