package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.ApiGatewayPropertyExtensionResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.ApiGatewayIntegrationVendorExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiGatewayIntegrationExtensionResolver implements ApiGatewayExtensionResolver {

  private final List<ApiGatewayPropertyExtensionResolver> propertyResolvers;

  @Override
  public VendorExtension resolve(OperationContext context) {
    final List<VendorExtension> extensionProperties = propertyResolvers.stream()
        .map(resolver -> resolver.resolve(context))
        .filter(property -> Objects.nonNull(property.getValue()))
        .collect(Collectors.toList());
    return new ApiGatewayIntegrationVendorExtension(extensionProperties);
  }
}
