package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension;

import springfox.documentation.service.VendorExtension;

public interface IntegrationExtension<T extends VendorExtension<?>> {

  T toVendorExtension();

}
