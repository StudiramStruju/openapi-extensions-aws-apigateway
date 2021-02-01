package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension;

import org.springframework.http.MediaType;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationRequestTemplateExtension implements IntegrationExtension<StringVendorExtension> {

  private MediaType mediaType;
  private String template;

  public IntegrationRequestTemplateExtension(MediaType mediaType, String template) {
    this.mediaType = Objects.requireNonNull(mediaType);
    this.template = Objects.requireNonNull(template);
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationRequestTemplateExtension mediaType(String mediaType) {
    this.mediaType = MediaType.parseMediaType(mediaType);
    return this;
  }

  public IntegrationRequestTemplateExtension mediaType(MediaType mediaType) {
    this.mediaType = mediaType;
    return this;
  }

  public IntegrationRequestTemplateExtension template(String template) {
    this.template = template;
    return this;
  }

  public MediaType mediaType() {
    return mediaType;
  }

  public String template() {
    return template;
  }

  public boolean matches(IntegrationRequestTemplateExtension template) {
    return mediaType.equals(template.mediaType());
  }

  public IntegrationRequestTemplateExtension update(IntegrationRequestTemplateExtension template) {
    return this.template(template.template());
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    Objects.requireNonNull(mediaType);
    Objects.requireNonNull(template);
    return new StringVendorExtension(mediaType.toString(), template);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationRequestTemplateExtension)) return false;
    IntegrationRequestTemplateExtension that = (IntegrationRequestTemplateExtension) object;
    return mediaType.equals(that.mediaType) &&
        template.equals(that.template);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mediaType, template);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationRequestTemplateExtension.class.getSimpleName() + "[", "]")
        .add("mediaType=" + mediaType)
        .add("template='" + template + "'")
        .toString();
  }

  public static class Builder {
    private MediaType mediaType;
    private String template;

    Builder() {
    }

    public Builder mediaType(MediaType mediaType) {
      this.mediaType = mediaType;
      return this;
    }

    public Builder template(String template) {
      this.template = template;
      return this;
    }

    public IntegrationRequestTemplateExtension build() {
      return new IntegrationRequestTemplateExtension(mediaType, template);
    }
  }
}
