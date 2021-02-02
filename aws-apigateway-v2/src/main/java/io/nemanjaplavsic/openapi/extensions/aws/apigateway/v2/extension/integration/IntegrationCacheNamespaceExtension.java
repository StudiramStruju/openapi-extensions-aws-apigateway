package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import org.springframework.util.StringUtils;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationCacheNamespaceExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "cacheNamespace";

  private String cacheNamespace;

  public IntegrationCacheNamespaceExtension(String cacheNamespace) {
    this.cacheNamespace = cacheNamespace;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String cacheNamespace() {
    return cacheNamespace;
  }

  public IntegrationCacheNamespaceExtension cacheNamespace(String cacheNamespace) {
    this.cacheNamespace = cacheNamespace;
    return this;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, cacheNamespace);
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
    return cacheNamespace.equals(that.cacheNamespace);
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

  public static class Builder {
    private String cacheNamespace;

    Builder() {
    }

    public Builder cacheNamespace(String cacheNamespace) {
      this.cacheNamespace = cacheNamespace;
      return this;
    }

    public IntegrationCacheNamespaceExtension build() {
      return new IntegrationCacheNamespaceExtension(cacheNamespace);
    }
  }
}
