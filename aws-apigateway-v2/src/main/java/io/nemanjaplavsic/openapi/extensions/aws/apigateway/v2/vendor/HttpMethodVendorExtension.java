package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.HttpMethod;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HttpMethodVendorExtension extends StringVendorExtension {

  public static final String NAME = "httpMethod";

  public HttpMethodVendorExtension(HttpMethod value) {
    super(NAME, value.key());
  }

  public HttpMethodVendorExtension(org.springframework.http.HttpMethod value) {
    this(HttpMethod.from(value.name()));
  }
}
