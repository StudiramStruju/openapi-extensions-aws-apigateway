package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationUriExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Objects;

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

    if (StringUtils.hasText(annotationUri)) {
      return IntegrationUriExtension.builder()
          .uri(normalizeUri(annotationUri) + context.requestMappingPattern())
          .build();
    }

    return IntegrationUriExtension.builder()
        .uri(getUri() + getBasePath() +context.requestMappingPattern())
        .build();
  }

  public String getBasePath() {
   return normalizeBasePath(properties.getBasePath());
  }

  public String getUri() {
    final String uri = properties.getUri();
    return normalizeUri(uri);
  }

  public String normalizeBasePath(String basePath) {
    if (Objects.nonNull(basePath)) {
      if (basePath.startsWith("/")) {
        return basePath;
      } else {
        return "/" + basePath;
      }
    }
    return Objects.requireNonNullElse(basePath, "");
  }

  public String normalizeUri(String uri) {
    if (Objects.nonNull(uri) && uri.endsWith("/")) {
      return uri.substring(uri.length() - 1);
    }
    return Objects.requireNonNullElse(uri, "");
  }
}
