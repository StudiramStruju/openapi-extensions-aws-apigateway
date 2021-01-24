package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;
import springfox.documentation.service.ListVendorExtension;

import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CacheKeyParametersVendorExtension extends ListVendorExtension<String> {

  public static final String NAME = "cacheKeyParameters";

  public CacheKeyParametersVendorExtension(@Nullable List<String> values) {
    super(NAME, values);
  }
}
