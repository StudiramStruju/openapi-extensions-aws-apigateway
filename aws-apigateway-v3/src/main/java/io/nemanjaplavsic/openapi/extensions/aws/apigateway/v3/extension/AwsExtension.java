package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension;

import org.springframework.lang.Nullable;

public interface AwsExtension<T> {

  String getExtensionKey();

  @Nullable
  T getExtensionValue();
}
