package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StatusCodeVendorExtension extends StringVendorExtension {

  public static final String NAME = "statusCode";

  public StatusCodeVendorExtension(int statusCode) {
    super(NAME, String.valueOf(statusCode));
  }
}
