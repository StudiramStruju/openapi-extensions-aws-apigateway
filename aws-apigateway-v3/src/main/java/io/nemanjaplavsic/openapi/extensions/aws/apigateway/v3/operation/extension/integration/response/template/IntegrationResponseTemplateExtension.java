package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.template;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ConvertableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ValidatableExtension;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationResponseTemplateExtension implements ConvertableExtension<String>, ValidatableExtension {

  public static final String DEFAULT_TEMPLATE = "$input.body";
  public static final String DEFAULT_JSON_TEMPLATE = "$input.json('$')";

  private final MediaType mediaType;
  private final String template;

  public IntegrationResponseTemplateExtension(String mediaType, String template) throws InvalidMediaTypeException {
    this(MediaType.parseMediaType(mediaType), template);
  }

  public IntegrationResponseTemplateExtension(MediaType mediaType, @Nullable String template) {
    this.mediaType = Objects.requireNonNull(mediaType);
    if (MediaType.APPLICATION_JSON.equals(this.mediaType)) {
      this.template = Objects.requireNonNullElse(template, DEFAULT_JSON_TEMPLATE);
    } else {
      this.template = Objects.requireNonNullElse(template, DEFAULT_TEMPLATE);
    }
  }

  public MediaType getMediaType() {
    return mediaType;
  }

  public String getTemplate() {
    return template;
  }

  public boolean matches(IntegrationResponseTemplateExtension template) {
    return Objects.equals(mediaType, template.getMediaType());
  }

  public String getExtensionKey() {
    return mediaType.toString();
  }

  public String getExtensionValue() {
    return template;
  }

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
