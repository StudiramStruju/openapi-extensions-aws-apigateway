package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TimeoutInMillisVendorExtension extends StringVendorExtension {

  public static final String NAME = "timeoutInMillis";
  public static final int DEFAULT = 29000;

  public TimeoutInMillisVendorExtension(int timeout) {
    super(NAME, String.valueOf(timeout));
  }
}
