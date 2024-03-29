package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationCacheNamespaceExtension implements ApiGatewayIntegrationExtension<String> {

  public static final String NAME = "cacheNamespace";

  @Nullable
  private final String cacheNamespace;

  public IntegrationCacheNamespaceExtension(@Nullable String cacheNamespace) {
    this.cacheNamespace = cacheNamespace;
  }

  @Nullable
  public String getCacheNamespace() {
    return cacheNamespace;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  @Nullable
  public String getExtensionValue() {
    return getCacheNamespace();
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(cacheNamespace);
      return StringUtils.hasText(cacheNamespace);
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationCacheNamespaceExtension)) return false;
    IntegrationCacheNamespaceExtension that = (IntegrationCacheNamespaceExtension) object;
    return Objects.equals(cacheNamespace, that.cacheNamespace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cacheNamespace);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationCacheNamespaceExtension.class.getSimpleName() + "[", "]")
        .add("cacheNamespace='" + cacheNamespace + "'")
        .toString();
  }
}
