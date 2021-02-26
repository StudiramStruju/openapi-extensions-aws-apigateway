package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security.extension;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ConvertableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ValidatableExtension;
import org.springframework.lang.NonNull;

public interface ApiGatewaySecurityExtension<T> extends ConvertableExtension<T>, ValidatableExtension {

  @NonNull
  @Override
  T getExtensionValue();
}
