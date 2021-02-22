package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.ApiGatewayOperationExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.service.contexts.OperationContext;

public interface OperationBuilderExtensionResolver<E extends ApiGatewayOperationExtension<? extends VendorExtension<?>>> {

  E resolve(OperationContext context);
}
