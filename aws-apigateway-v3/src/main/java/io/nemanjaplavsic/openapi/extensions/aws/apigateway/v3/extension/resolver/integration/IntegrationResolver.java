package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration.IntegrationExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import java.util.stream.Stream;

public interface IntegrationResolver<E extends IntegrationExtension<?>> {

  @Nullable
  E resolve(Operation operation, HandlerMethod handlerMethod);

  @Nullable
  default String getFirstNonEmptyValue(String... values) {
    return Stream.of(values)
        .filter(v -> !StringUtils.isEmpty(v))
        .findFirst()
        .orElse(null);
  }

}
