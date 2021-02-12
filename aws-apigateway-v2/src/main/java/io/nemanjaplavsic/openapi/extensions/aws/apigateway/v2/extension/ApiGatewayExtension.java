package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension;

import springfox.documentation.service.VendorExtension;

public interface ApiGatewayExtension<T extends VendorExtension<?>> {
  T toVendorExtension();
}
