package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.service.contexts.OperationContext;

public interface ApiGatewayIntegrationExtensionPropertyResolver {
  VendorExtension resolve(OperationContext context);
}
