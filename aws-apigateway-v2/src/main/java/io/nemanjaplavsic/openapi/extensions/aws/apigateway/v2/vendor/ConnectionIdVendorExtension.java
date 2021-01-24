package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConnectionIdVendorExtension extends StringVendorExtension {

  public static final String NAME = "connectionId";

  public ConnectionIdVendorExtension(String value) {
    super(NAME, value);
  }
}
