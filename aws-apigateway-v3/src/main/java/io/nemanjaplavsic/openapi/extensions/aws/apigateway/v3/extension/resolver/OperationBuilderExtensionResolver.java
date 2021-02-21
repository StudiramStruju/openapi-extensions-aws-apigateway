package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.resolver;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.ApiGatewayMethodExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.service.contexts.OperationContext;

public interface OperationBuilderExtensionResolver<E extends ApiGatewayMethodExtension<? extends VendorExtension<?>>> {

  E resolve(OperationContext context);
}
