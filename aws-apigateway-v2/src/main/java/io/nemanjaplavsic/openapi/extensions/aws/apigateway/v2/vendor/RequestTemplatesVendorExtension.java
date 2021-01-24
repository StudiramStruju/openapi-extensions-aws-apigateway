package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.VendorExtension;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RequestTemplatesVendorExtension extends ObjectVendorExtension {

  public static final String NAME = "requestTemplates";

  public RequestTemplatesVendorExtension(List<VendorExtension<?>> properties) {
    super(NAME);
    properties.forEach(this::addProperty);
  }
}
