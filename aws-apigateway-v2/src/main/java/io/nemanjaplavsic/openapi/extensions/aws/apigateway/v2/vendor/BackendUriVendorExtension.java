package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;
import springfox.documentation.service.StringVendorExtension;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BackendUriVendorExtension extends StringVendorExtension {

  public static final String NAME = "uri";

  public BackendUriVendorExtension(@Nullable String uri) {
    super(NAME, uri);
  }
}
