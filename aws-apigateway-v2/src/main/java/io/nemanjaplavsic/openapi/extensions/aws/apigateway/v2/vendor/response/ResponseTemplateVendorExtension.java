package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.MediaType;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponseTemplateVendorExtension extends StringVendorExtension {

  public static final String DEFAULT_TEMPLATE = "$input.json('$')";

  public ResponseTemplateVendorExtension(MediaType mediaType, String template) {
    super(mediaType.toString(), template);
  }

  public ResponseTemplateVendorExtension(String mediaType, String template) {
    this(MediaType.valueOf(mediaType), template);
  }
}