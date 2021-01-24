package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.RequestParamVendorExtension;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;
import springfox.documentation.service.ObjectVendorExtension;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RequestParametersVendorExtension extends ObjectVendorExtension {

  public static final String NAME = "requestParameters";

  public RequestParametersVendorExtension(@Nullable List<RequestParamVendorExtension> properties) {
    super(NAME);
    if (Objects.nonNull(properties)) {
      properties.forEach(this::addProperty);
    }
  }
}
