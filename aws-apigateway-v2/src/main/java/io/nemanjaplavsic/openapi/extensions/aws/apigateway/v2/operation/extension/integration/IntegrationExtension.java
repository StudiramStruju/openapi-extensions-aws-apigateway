package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.AwsExtension;
import springfox.documentation.service.VendorExtension;

public interface IntegrationExtension<T extends VendorExtension<?>> extends AwsExtension<T> {

  boolean isValid();

}
