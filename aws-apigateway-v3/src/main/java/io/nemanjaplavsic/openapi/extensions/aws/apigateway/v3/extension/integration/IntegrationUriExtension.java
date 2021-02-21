package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration;

import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationUriExtension implements IntegrationExtension<String> {

  public static final String NAME = "uri";

  private String uri;

  public IntegrationUriExtension(String uri) {
    this.uri = uri;
  }

  public IntegrationUriExtension uri(String uri) {
    this.uri = uri;
    return this;
  }

  public String uri() {
    return uri;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  public String getExtensionValue() {
    return uri;
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

}
