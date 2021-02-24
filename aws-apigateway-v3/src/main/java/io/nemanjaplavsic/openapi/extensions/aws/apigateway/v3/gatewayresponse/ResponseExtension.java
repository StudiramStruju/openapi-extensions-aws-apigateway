package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.gatewayresponse;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.AwsExtension;

public interface ResponseExtension<T> extends AwsExtension<T> {

  boolean isValid();
}
