package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request;


import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParamType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StaticValueParamVendorExtension extends RequestParamVendorExtension {

  public StaticValueParamVendorExtension(IntegrationRequestParamType integrationRequestParamType,
                                         String integrationRequestParamName,
                                         String staticValueName) {
    super(
        String.format("integration.request.%s.%s", integrationRequestParamType.key(), integrationRequestParamName),
        String.format("%s", staticValueName)
    );
  }
}
