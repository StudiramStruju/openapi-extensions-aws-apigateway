package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class RequestParamVendorExtension extends StringVendorExtension {

  protected RequestParamVendorExtension(String name, String value) {
    super(name, value);
  }
}
