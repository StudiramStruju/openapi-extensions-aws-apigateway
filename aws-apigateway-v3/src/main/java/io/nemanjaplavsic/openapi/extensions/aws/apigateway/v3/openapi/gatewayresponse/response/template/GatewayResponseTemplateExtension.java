package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.openapi.gatewayresponse.response.template;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ConvertableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ValidatableExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.StringJoiner;

@Slf4j
public class GatewayResponseTemplateExtension implements ConvertableExtension<String>, ValidatableExtension {

  public static final String DEFAULT_TEMPLATE = "{\n" +
      "  \"message\":\"$context.error.messageString\",\n" +
      "  \"path\": \"$context.path\",\n" +
      "  \"exception\": \"$context.error.responseType\",\n" +
      "  \"timestamp\": \"$context.requestTime\"\n" +
      "}";

  public static final String SIMPLE_TEMPLATE = "{\"message\":$context.error.messageString}";

  private final MediaType mediaType;
  private final String template;

  public GatewayResponseTemplateExtension(String mediaType, String template) throws InvalidMediaTypeException {
    this(MediaType.parseMediaType(mediaType), template);
  }

  public GatewayResponseTemplateExtension(MediaType mediaType, String template) {
    this.mediaType = Objects.requireNonNull(mediaType);
    this.template = template;
  }

  public static GatewayResponseTemplateExtension defaultTemplate() {
    return new GatewayResponseTemplateExtension(MediaType.APPLICATION_JSON, DEFAULT_TEMPLATE);
  }

  public static GatewayResponseTemplateExtension simpleTemplate() {
    return new GatewayResponseTemplateExtension(MediaType.APPLICATION_JSON, SIMPLE_TEMPLATE);
  }

  public MediaType getMediaType() {
    return mediaType;
  }

  public String getTemplate() {
    return template;
  }

  public boolean matches(GatewayResponseTemplateExtension template) {
    return Objects.equals(mediaType, template.getMediaType());
  }

  @Override
  public String getExtensionKey() {
    return mediaType.toString();
  }

  @Override
  public String getExtensionValue() {
    return template;
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
    if (!(object instanceof GatewayResponseTemplateExtension)) return false;
    GatewayResponseTemplateExtension that = (GatewayResponseTemplateExtension) object;
    return mediaType.equals(that.mediaType) &&
        Objects.equals(template, that.template);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mediaType, template);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GatewayResponseTemplateExtension.class.getSimpleName() + "[", "]")
        .add("mediaType=" + mediaType)
        .add("template='" + template + "'")
        .toString();
  }
}
