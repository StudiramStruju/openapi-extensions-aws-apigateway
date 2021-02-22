package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.AwsExtension;

public interface IntegrationExtension<T> extends AwsExtension<T> {

  boolean isValid();

}
