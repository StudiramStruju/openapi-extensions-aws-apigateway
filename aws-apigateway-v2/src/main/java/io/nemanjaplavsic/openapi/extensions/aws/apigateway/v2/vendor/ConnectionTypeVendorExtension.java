package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ConnectionType;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConnectionTypeVendorExtension extends StringVendorExtension {

  public static final String NAME = "connectionType";

  public ConnectionTypeVendorExtension(ConnectionType connectionType) {
    super(NAME, connectionType.key());
  }
}
