package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.VendorExtension;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponseVendorExtension extends ObjectVendorExtension {

  public ResponseVendorExtension(String responseStatusPattern, List<VendorExtension<?>> properties) {
    super(responseStatusPattern);
    properties.forEach(this::addProperty);
  }
}
