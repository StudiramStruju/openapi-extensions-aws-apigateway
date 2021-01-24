package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationType;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IntegrationTypeVendorExtension extends StringVendorExtension {

  public static final String NAME = "type";

  public IntegrationTypeVendorExtension(IntegrationType integrationType) {
    super(NAME, integrationType.key());
  }
}
