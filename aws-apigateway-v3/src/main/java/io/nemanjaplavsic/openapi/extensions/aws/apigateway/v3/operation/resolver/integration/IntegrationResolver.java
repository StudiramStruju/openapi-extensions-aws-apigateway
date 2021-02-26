package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.ApiGatewayIntegrationExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import java.util.stream.Stream;

public interface IntegrationResolver<E extends ApiGatewayIntegrationExtension<?>> {

  @Nullable
  E resolve(Operation operation, HandlerMethod handlerMethod);

  @Nullable
  default String getFirstNonEmptyValue(String... values) {
    return Stream.of(values)
        .filter(StringUtils::hasText)
        .findFirst()
        .orElse(null);
  }

}
