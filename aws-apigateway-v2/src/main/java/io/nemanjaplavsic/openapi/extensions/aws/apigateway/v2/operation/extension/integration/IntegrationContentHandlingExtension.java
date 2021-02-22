package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import org.springframework.lang.Nullable;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationContentHandlingExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "contentHandling";

  private ContentHandling contentHandling;

  public IntegrationContentHandlingExtension() {
    this(null);
  }

  public IntegrationContentHandlingExtension(@Nullable ContentHandling contentHandling) {
    this.contentHandling = Objects.requireNonNullElse(contentHandling, ContentHandling.NONE);
  }

  public IntegrationContentHandlingExtension contentHandling(@Nullable ContentHandling contentHandling) {
    this.contentHandling = Objects.requireNonNullElse(contentHandling, ContentHandling.NONE);
    return this;
  }

  public ContentHandling contentHandling() {
    return contentHandling;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, this.contentHandling.key());
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(contentHandling);
      Objects.requireNonNull(contentHandling.key());
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationContentHandlingExtension)) return false;
    IntegrationContentHandlingExtension that = (IntegrationContentHandlingExtension) object;
    return contentHandling == that.contentHandling;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationContentHandlingExtension.class.getSimpleName() + "[", "]")
        .add("contentHandling=" + contentHandling)
        .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(contentHandling);
  }
}
