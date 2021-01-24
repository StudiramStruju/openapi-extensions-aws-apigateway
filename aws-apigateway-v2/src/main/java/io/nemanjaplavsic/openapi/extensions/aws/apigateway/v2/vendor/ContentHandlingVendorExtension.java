package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContentHandlingVendorExtension extends StringVendorExtension {

  public static final String NAME = "contentHandling";

  public ContentHandlingVendorExtension(ContentHandling contentHandling) {
    super(NAME, contentHandling.key());
  }
}
