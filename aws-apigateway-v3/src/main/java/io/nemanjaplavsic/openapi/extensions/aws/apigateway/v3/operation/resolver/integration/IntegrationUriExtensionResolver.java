package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.ApiGatewayServiceProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationUriExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

public class IntegrationUriExtensionResolver implements IntegrationResolver<IntegrationUriExtension> {

  private final ApiGatewayServiceProperties properties;

  public IntegrationUriExtensionResolver(ApiGatewayServiceProperties properties) {
    this.properties = properties;
  }

  @Override
  public IntegrationUriExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    final String annotationUri = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::uri)
        .orElse(null);

    final String uri = resolveUri(annotationUri, handlerMethod);
    return new IntegrationUriExtension(uri);
  }

  public String resolveUri(String annotationUri, HandlerMethod handlerMethod) {
    final String methodPath = extractMethodPath(handlerMethod);
    if (StringUtils.hasText(annotationUri)) {
      return normalizeFrontSlash(annotationUri) + methodPath;
    }
    return getUri() + getBasePath() + methodPath;
  }

  public String extractMethodPath(HandlerMethod handlerMethod) {
    final Path path = Optional.ofNullable(handlerMethod.getBeanType().getAnnotation(RequestMapping.class))
        .map(requestMapping -> extractAnnotationPath(requestMapping.value(), requestMapping.path())
        ).orElse(Path.of(""));

    Optional.ofNullable(handlerMethod.getMethodAnnotation(RequestMapping.class))
        .ifPresent(annotation -> path.resolve(extractAnnotationPath(annotation.value(), annotation.path())));
    Optional.ofNullable(handlerMethod.getMethodAnnotation(GetMapping.class))
        .ifPresent(annotation -> path.resolve(extractAnnotationPath(annotation.value(), annotation.path())));
    Optional.ofNullable(handlerMethod.getMethodAnnotation(PostMapping.class))
        .ifPresent(annotation -> path.resolve(extractAnnotationPath(annotation.value(), annotation.path())));
    Optional.ofNullable(handlerMethod.getMethodAnnotation(PutMapping.class))
        .ifPresent(annotation -> path.resolve(extractAnnotationPath(annotation.value(), annotation.path())));
    Optional.ofNullable(handlerMethod.getMethodAnnotation(DeleteMapping.class))
        .ifPresent(annotation -> path.resolve(extractAnnotationPath(annotation.value(), annotation.path())));
    Optional.ofNullable(handlerMethod.getMethodAnnotation(PatchMapping.class))
        .ifPresent(annotation -> path.resolve(extractAnnotationPath(annotation.value(), annotation.path())));

    return path.normalize().toString();
  }

  public Path extractAnnotationPath(String[] value, String[] path) {
    final Path valuePath = toPath(value);
    if (Objects.nonNull(valuePath)) {
      return valuePath;
    }
    final Path pathPath = toPath(path);
    if (Objects.nonNull(pathPath)) {
      return pathPath;
    }
    return Path.of("");
  }

  public Path toPath(String[] value) {
    final String path = getFirstNonEmptyValue(value);
    if (StringUtils.hasText(path)) {
      return Path.of(path);
    }
    return null;
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
