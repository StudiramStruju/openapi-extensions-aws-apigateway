package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request;


import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParameterType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StaticValueParamVendorExtension extends RequestParamVendorExtension {

  public StaticValueParamVendorExtension(IntegrationRequestParameterType integrationRequestParameterType,
                                         String integrationRequestParamName,
                                         String staticValueName) {
    super(
        String.format("integration.request.%s.%s", integrationRequestParameterType.key(), integrationRequestParamName),
        String.format("%s", staticValueName)
    );
  }
}
