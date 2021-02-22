package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.security.extension;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.AwsExtension;
import springfox.documentation.service.VendorExtension;

public interface ApiGatewaySecurityExtension<T extends VendorExtension<?>> extends AwsExtension<T> {
}
