package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParamType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.MethodRequestParamType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MethodRequestParamVendorExtension extends RequestParamVendorExtension {

  public MethodRequestParamVendorExtension(IntegrationRequestParamType integrationRequestParamType,
                                           String integrationRequestParamName,
                                           MethodRequestParamType methodRequestParamType,
                                           String methodRequestParamName) {
    super(
        String.format("integration.request.%s.%s", integrationRequestParamType.key(), integrationRequestParamName),
        String.format("method.request.%s.%s", methodRequestParamType.key(), methodRequestParamName)
    );
  }
}
