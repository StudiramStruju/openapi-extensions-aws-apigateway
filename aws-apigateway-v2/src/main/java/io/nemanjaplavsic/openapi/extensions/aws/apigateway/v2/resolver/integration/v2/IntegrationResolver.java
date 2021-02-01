package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.v2;

import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.List;

public interface IntegrationResolver {

  List<VendorExtension> resolve(OperationContext context);
}
