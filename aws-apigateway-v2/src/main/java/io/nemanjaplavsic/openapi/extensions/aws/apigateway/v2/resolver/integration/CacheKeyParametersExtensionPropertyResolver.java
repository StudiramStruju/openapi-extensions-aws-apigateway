package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.OpenApiProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.OpenApiProperties.OpenApiExtensionProperties.OpenApiExtensionAwsProperties.OpenApiExtensionAwsApiGatewayProperties.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.CacheKeyParametersVendorExtension;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Arrays;
import java.util.Objects;

@Component
public class CacheKeyParametersExtensionPropertyResolver implements ApiGatewayIntegrationExtensionPropertyResolver {

  private final ApiGatewayServiceProperties properties;

  public CacheKeyParametersExtensionPropertyResolver(OpenApiProperties properties) {
    this.properties = properties.getExtension().getAws().getApiGateway().getService();
  }

  @Override
  public CacheKeyParametersVendorExtension resolve(OperationContext context) {

    final String[] annotationCacheKeyParameters = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::cacheKeyParameters)
        .orNull();

    if (Objects.nonNull(annotationCacheKeyParameters) && annotationCacheKeyParameters.length != 0) {
      return new CacheKeyParametersVendorExtension(Arrays.asList(annotationCacheKeyParameters));
    }

    final String[] cacheKeyParameters = properties.getCacheKeyParameters();
    if (Objects.nonNull(cacheKeyParameters) && cacheKeyParameters.length != 0) {
      return new CacheKeyParametersVendorExtension(Arrays.asList(cacheKeyParameters));
    }

    return new CacheKeyParametersVendorExtension(null);
  }
}
