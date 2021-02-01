package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.OpenApiProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.BackendUriVendorExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.nio.file.Path;

@Slf4j
@Component
public class BackendUriExtensionResolver implements ApiGatewayPropertyExtensionResolver {

  private final ApiGatewayServiceProperties properties;

  public BackendUriExtensionResolver(OpenApiProperties properties) {
    this.properties = properties.getExtension().getAws().getApiGateway().getService();
  }

  @Override
  public BackendUriVendorExtension resolve(OperationContext context) {

    final String requestMappingPattern = context.requestMappingPattern();
    final String basePath = properties.getBasePath();
    final String annotationUri = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::uri)
        .orNull();

    if (StringUtils.hasText(annotationUri)) {
      return resolve(annotationUri, requestMappingPattern, basePath);
    } else if (StringUtils.hasText(properties.getUri())) {
      return resolve(properties.getUri(), requestMappingPattern, basePath);
    } else {
      return new BackendUriVendorExtension(null);
    }
  }

  public BackendUriVendorExtension resolve(String uri,
                                           String requestMappingPattern,
                                           @Nullable String basePath) {
    if (StringUtils.hasText(basePath)) {
      final String uriPath = Path.of(basePath, requestMappingPattern).normalize().toString();
      return new BackendUriVendorExtension(uri.concat(uriPath));
    } else {
      return new BackendUriVendorExtension(uri.concat(requestMappingPattern));
    }
  }
}
