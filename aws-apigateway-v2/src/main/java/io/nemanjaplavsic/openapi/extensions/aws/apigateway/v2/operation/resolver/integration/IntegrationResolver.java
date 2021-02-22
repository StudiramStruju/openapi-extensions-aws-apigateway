package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationExtension;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.stream.Stream;

public interface IntegrationResolver<E extends IntegrationExtension<?  extends VendorExtension<?>>> {

  @Nullable
  E resolve(OperationContext context);

  @Nullable
  default String getFirstNonEmptyValue(String... values) {
    return Stream.of(values)
        .filter(v -> !StringUtils.isEmpty(v))
        .findFirst()
        .orElse(null);
  }

}
