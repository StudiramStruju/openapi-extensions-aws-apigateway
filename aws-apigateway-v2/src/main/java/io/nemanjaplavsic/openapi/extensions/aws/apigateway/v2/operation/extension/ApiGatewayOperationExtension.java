package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.AwsExtension;
import springfox.documentation.service.VendorExtension;

public interface ApiGatewayOperationExtension<T extends VendorExtension<?>> extends AwsExtension<T> {
}
