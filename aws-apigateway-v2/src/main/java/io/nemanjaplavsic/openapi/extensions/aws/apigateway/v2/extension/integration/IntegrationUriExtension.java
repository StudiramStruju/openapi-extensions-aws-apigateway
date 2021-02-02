package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import org.springframework.util.StringUtils;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationUriExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "uri";

  private String uri;

  public IntegrationUriExtension(String uri) {
    this.uri = uri;
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationUriExtension uri(String uri) {
    this.uri = uri;
    return this;
  }

  public String uri() {
    return uri;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, uri);
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(uri);
      return StringUtils.hasText(uri);
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationUriExtension)) return false;
    IntegrationUriExtension that = (IntegrationUriExtension) object;
    return uri.equals(that.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uri);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationUriExtension.class.getSimpleName() + "[", "]")
        .add("uri='" + uri + "'")
        .toString();
  }

  public static class Builder {
    private String uri;

    Builder() {
    }

    public Builder uri(String uri) {
      this.uri = uri;
      return this;
    }

    public IntegrationUriExtension build() {
      return new IntegrationUriExtension(uri);
    }
  }
}
