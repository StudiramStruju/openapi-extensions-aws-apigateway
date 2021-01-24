package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseVendorExtension;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.ObjectVendorExtension;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponsesVendorExtension extends ObjectVendorExtension {

  public static final String NAME = "responses";

  public ResponsesVendorExtension(List<ResponseVendorExtension> properties) {
    super(NAME);
    properties.forEach(this::addProperty);
  }
}
