package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.ObjectVendorExtension;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponseTemplatesVendorExtension extends ObjectVendorExtension {

  public static final String NAME = "responseTemplates";

  public ResponseTemplatesVendorExtension() {
    super(NAME);
  }

  public ResponseTemplatesVendorExtension(List<ResponseTemplateVendorExtension> extensions) {
    super(NAME);
    extensions.stream().filter(prop -> Objects.nonNull(prop.getValue())).forEach(this::addProperty);
  }


}
