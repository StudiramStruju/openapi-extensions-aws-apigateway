package io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension;

import org.springframework.lang.Nullable;

public interface ConvertableExtension<T> {

  String getExtensionKey();

  @Nullable
  T getExtensionValue();
}
