package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.MediaType;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RequestTemplateVendorExtension extends StringVendorExtension {

  public RequestTemplateVendorExtension(MediaType mediaType, String template) {
    super(mediaType.toString(), template);
  }
}
