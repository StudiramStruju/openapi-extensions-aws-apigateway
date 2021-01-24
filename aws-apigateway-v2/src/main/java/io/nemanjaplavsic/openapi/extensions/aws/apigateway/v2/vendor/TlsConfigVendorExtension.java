package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TlsConfigVendorExtension extends ObjectVendorExtension {

  public static final String NAME = "tlsConfig";

  public TlsConfigVendorExtension(Boolean insecureSkipVerification) {
    super(NAME);
    this.addProperty(new StringVendorExtension("insecureSkipVerification", insecureSkipVerification.toString()));
  }

  public TlsConfigVendorExtension(String serverNameToVerify) {
    super(NAME);
    this.addProperty(new StringVendorExtension("serverNameToVerify", serverNameToVerify));
  }
}
