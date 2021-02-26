package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.configuration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.ApiGatewayIntegrationOperationCustomizer;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.ApiGatewayIntegrationExtensionResolver;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApiGatewayServiceProperties.class)
public class OpenApiAutoConfiguration {

  @Bean
  public ApiGatewayIntegrationExtensionResolver apiGatewayIntegrationExtensionResolver(ApiGatewayServiceProperties properties) {
    return new ApiGatewayIntegrationExtensionResolver(
        new IntegrationCacheKeyParametersResolver(properties),
        new IntegrationCacheNamespaceResolver(properties),
        new IntegrationConnectionIdResolver(properties),
        new IntegrationConnectionTypeResolver(properties),
        new IntegrationCredentialsResolver(properties),
        new IntegrationHttpMethodResolver(),
        new IntegrationIntegrationTypeResolver(properties),
        new IntegrationPassTroughBehaviourResolver(properties),
        new IntegrationTimeoutInMillisResolver(properties),
        new IntegrationUriExtensionResolver(properties),
        new IntegrationRequestParametersResolver(),
        new IntegrationRequestTemplatesResolver(),
        new IntegrationResponsesResolver()
    );
  }

  @Bean
  @ConditionalOnProperty(value = "openapi.extension.aws.api-gateway.enabled-operation-plugin", havingValue = "true", matchIfMissing = true)
  public ApiGatewayIntegrationOperationCustomizer apiGatewayIntegrationOperationCustomizer(ApiGatewayIntegrationExtensionResolver resolver) {
    return new ApiGatewayIntegrationOperationCustomizer(resolver);
  }

}
