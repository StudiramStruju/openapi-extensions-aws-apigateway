package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.ContentHandlingVendorExtension;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.VendorExtension;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponseVendorExtension extends ObjectVendorExtension {

  public ResponseVendorExtension(String responseStatusPattern) {
    super(responseStatusPattern);
  }

  public ResponseVendorExtension(String responseStatusPattern, List<VendorExtension<?>> properties) {
    super(responseStatusPattern);
    properties.stream().filter(prop -> Objects.nonNull(prop.getValue())).forEach(this::addProperty);
  }

  public ResponseVendorExtension statusCode(int statusCode) {
    return this.statusCode(new StatusCodeVendorExtension(statusCode));
  }

  public ResponseVendorExtension statusCode(StatusCodeVendorExtension statusCodeVendorExtension) {
    if (exists(StatusCodeVendorExtension.NAME)) {
      this.replaceProperty(statusCodeVendorExtension);
    } else {
      this.addProperty(statusCodeVendorExtension);
    }
    return this;
  }

  public ResponseVendorExtension contentHandling(ContentHandling contentHandling) {
    return this.contentHandling(new ContentHandlingVendorExtension(contentHandling));
  }

  public ResponseVendorExtension contentHandling(ContentHandlingVendorExtension contentHandlingVendorExtension) {
    if (exists(ContentHandlingVendorExtension.NAME)) {
      this.replaceProperty(contentHandlingVendorExtension);
    } else {
      this.addProperty(contentHandlingVendorExtension);
    }
    return this;
  }

  public ResponseVendorExtension responseTemplates(ResponseTemplatesVendorExtension responseTemplates) {
    if (exists(ResponseTemplatesVendorExtension.NAME)) {
      this.replaceProperty(responseTemplates);
    } else {
      this.addProperty(responseTemplates);
    }
    return this;
  }

  public ResponseVendorExtension responseParameters(ResponseParametersVendorExtension responseParameters) {
    if (exists(ResponseParametersVendorExtension.NAME)) {
      this.replaceProperty(responseParameters);
    } else {
      this.addProperty(responseParameters);
    }
    return this;
  }

  public boolean exists(String name) {
    return this.getValue().stream()
        .anyMatch(vendorExtension -> vendorExtension.getName().equals(name));
  }
}
