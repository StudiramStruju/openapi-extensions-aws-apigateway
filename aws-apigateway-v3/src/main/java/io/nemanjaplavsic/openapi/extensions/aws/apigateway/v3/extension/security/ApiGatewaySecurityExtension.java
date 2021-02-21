package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.security;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.AwsExtension;
import org.springframework.lang.NonNull;

public interface ApiGatewaySecurityExtension<T> extends AwsExtension<T> {

  @NonNull
  @Override
  T getExtensionValue();
}
