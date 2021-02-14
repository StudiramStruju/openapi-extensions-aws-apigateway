package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationUriExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.service.contexts.OperationContext;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(ApiGatewayServiceProperties.class)
public class IntegrationUriExtensionResolver implements IntegrationResolver<IntegrationUriExtension> {

  private final ApiGatewayServiceProperties properties;

  @Override
  public IntegrationUriExtension resolve(OperationContext context) {
    final String annotationUri = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::uri)
        .orNull();

    final String uri = resolveUri(annotationUri, context);
    return new IntegrationUriExtension(uri);
  }

  public String resolveUri(String annotationUri, OperationContext context) {
    if (StringUtils.hasText(annotationUri)) {
      return normalizeFrontSlash(annotationUri) + context.requestMappingPattern();
    }
    return getUri() + getBasePath() + context.requestMappingPattern();
  }

  public String getBasePath() {
    return normalizeBackSlash(properties.getBasePath());
  }

  public String getUri() {
    final String uri = properties.getUri();
    return normalizeFrontSlash(uri);
  }

  public String normalizeBackSlash(String basePath) {
    if (StringUtils.hasText(basePath)) {
      if (basePath.startsWith("/")) {
        return basePath;
      } else {
        return "/" + basePath;
      }
    }
    return "";
  }

  public String normalizeFrontSlash(String uri) {
    if (StringUtils.hasText(uri)) {
      if (uri.endsWith("/")) {
        return uri.substring(uri.length() - 1);
      }
      return uri;
    }
    return "";
  }

}
