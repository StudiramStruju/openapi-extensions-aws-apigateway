package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationExtension;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationResponseTemplateExtension implements IntegrationExtension<StringVendorExtension> {


  private final MediaType mediaType;
  private final String template;

  public IntegrationResponseTemplateExtension(String mediaType, String template) throws InvalidMediaTypeException {
    this(MediaType.parseMediaType(mediaType), template);
  }

  public IntegrationResponseTemplateExtension(MediaType mediaType, String template) {
    this.mediaType = Objects.requireNonNull(mediaType);
    this.template = Objects.requireNonNullElse(template, "$input.json('$')");
  }

  public MediaType mediaType() {
    return mediaType;
  }

  public String template() {
    return template;
  }

  public boolean matches(IntegrationResponseTemplateExtension template) {
    return Objects.equals(mediaType, template.mediaType());
  }

  @Override
  public StringVendorExtension toVendorExtension() {
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
    if (!(object instanceof IntegrationResponseTemplateExtension)) return false;
    IntegrationResponseTemplateExtension that = (IntegrationResponseTemplateExtension) object;
    return mediaType.equals(that.mediaType) &&
        Objects.equals(template, that.template);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mediaType, template);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationResponseTemplateExtension.class.getSimpleName() + "[", "]")
        .add("mediaType=" + mediaType)
        .add("template='" + template + "'")
        .toString();
  }
}
