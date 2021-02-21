package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.AwsExtension;

public interface IntegrationExtension<T> extends AwsExtension<T> {

  boolean isValid();

}
