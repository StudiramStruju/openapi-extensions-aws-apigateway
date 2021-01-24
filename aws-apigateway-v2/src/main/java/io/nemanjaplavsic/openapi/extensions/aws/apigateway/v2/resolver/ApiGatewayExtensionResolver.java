package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver;

import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.service.contexts.OperationContext;

public interface ApiGatewayExtensionResolver {

  VendorExtension resolve(OperationContext context);
}
