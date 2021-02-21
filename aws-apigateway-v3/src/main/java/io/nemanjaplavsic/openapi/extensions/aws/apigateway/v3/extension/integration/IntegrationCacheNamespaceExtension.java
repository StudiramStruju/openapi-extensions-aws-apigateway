package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationCacheNamespaceExtension implements IntegrationExtension<String> {

  public static final String NAME = "cacheNamespace";

  private final String cacheNamespace;

  public IntegrationCacheNamespaceExtension(@Nullable String cacheNamespace) {
    this.cacheNamespace = Objects.requireNonNull(cacheNamespace, "Cannot create instance of IntegrationCacheNamespaceExtension with null value!");
  }

  public String cacheNamespace() {
    return cacheNamespace;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  public String getExtensionValue() {
    return cacheNamespace;
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
