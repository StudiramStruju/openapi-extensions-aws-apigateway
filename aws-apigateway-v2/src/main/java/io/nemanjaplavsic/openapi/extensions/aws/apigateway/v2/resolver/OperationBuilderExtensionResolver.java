package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.ApiGatewayExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.service.contexts.OperationContext;

public interface OperationBuilderExtensionResolver<E extends ApiGatewayExtension<? extends VendorExtension<?>>> {

  E resolve(OperationContext context);
}
