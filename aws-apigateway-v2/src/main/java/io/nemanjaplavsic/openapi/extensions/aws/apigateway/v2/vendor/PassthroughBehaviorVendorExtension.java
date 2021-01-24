package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.PassThroughBehavior;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PassthroughBehaviorVendorExtension extends StringVendorExtension {

  public static final String NAME = "passthroughBehavior";

  public PassthroughBehaviorVendorExtension(PassThroughBehavior passThroughBehavior) {
    super(NAME, passThroughBehavior.key());
  }
}
