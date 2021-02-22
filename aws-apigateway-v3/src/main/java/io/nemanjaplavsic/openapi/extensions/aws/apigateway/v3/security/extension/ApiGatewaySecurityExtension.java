package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security.extension;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.AwsExtension;
import org.springframework.lang.NonNull;

public interface ApiGatewaySecurityExtension<T> extends AwsExtension<T> {

  @NonNull
  @Override
  T getExtensionValue();
}
