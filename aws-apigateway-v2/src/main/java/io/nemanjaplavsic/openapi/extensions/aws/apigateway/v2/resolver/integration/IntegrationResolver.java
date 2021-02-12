package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationExtension;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.stream.Stream;

public interface IntegrationResolver<E extends IntegrationExtension<?  extends VendorExtension<?>>> {

  E resolve(OperationContext context);

  @Nullable
  default String getFirstNonEmptyValue(String... values) {
    return Stream.of(values)
        .filter(v -> !StringUtils.isEmpty(v))
        .findFirst()
        .orElse(null);
  }

}
