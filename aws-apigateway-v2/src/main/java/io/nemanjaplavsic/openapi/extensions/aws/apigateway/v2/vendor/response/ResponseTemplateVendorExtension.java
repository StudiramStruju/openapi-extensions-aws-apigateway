package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.MediaType;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponseTemplateVendorExtension extends StringVendorExtension {

  public ResponseTemplateVendorExtension(MediaType mediaType, String template) {
    super(mediaType.toString(), template);
  }
}
