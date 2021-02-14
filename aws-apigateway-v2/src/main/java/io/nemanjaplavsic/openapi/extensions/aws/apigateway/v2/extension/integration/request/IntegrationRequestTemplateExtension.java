package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationExtension;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationRequestTemplateExtension implements IntegrationExtension<StringVendorExtension> {

  private final MediaType mediaType;
  private final String template;

  public IntegrationRequestTemplateExtension(String mediaType, String template) {
    this(MediaType.parseMediaType(mediaType), template);
  }

  public IntegrationRequestTemplateExtension(MediaType mediaType, String template) {
    this.mediaType = Objects.requireNonNull(mediaType);
    this.template = Objects.requireNonNull(template);
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

  @Override
  public StringVendorExtension toVendorExtension() {
    Objects.requireNonNull(mediaType);
    Objects.requireNonNull(template);
    return new StringVendorExtension(mediaType.toString(), template);
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(mediaType);
      Objects.requireNonNull(template);
      return StringUtils.hasText(template);
    } catch (NullPointerException e) {
      return false;
    }
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
}
