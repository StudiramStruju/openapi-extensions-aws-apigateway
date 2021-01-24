package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.Nullable;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.VendorExtension;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ApiGatewayIntegrationVendorExtension extends ObjectVendorExtension {

  public static final String NAME = "x-amazon-apigateway-integration";

  public ApiGatewayIntegrationVendorExtension(@Nullable List<VendorExtension> properties) {
    super(NAME);
    if (Objects.nonNull(properties)) {
      properties.forEach(this::addProperty);
    }
  }
}
