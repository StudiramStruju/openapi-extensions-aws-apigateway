package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.VendorExtension;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponseTemplatesVendorExtension extends ObjectVendorExtension {

  public static final String NAME = "responseTemplates";

  public ResponseTemplatesVendorExtension(List<VendorExtension<?>> extensions) {
    super(NAME);
    extensions.forEach(this::addProperty);
  }
}
