package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.MethodRequestParameterType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MethodRequestParamVendorExtension extends RequestParamVendorExtension {

  public MethodRequestParamVendorExtension(IntegrationRequestParameterType integrationRequestParameterType,
                                           String integrationRequestParamName,
                                           MethodRequestParameterType methodRequestParameterType,
                                           String methodRequestParamName) {
    super(
        String.format("integration.request.%s.%s", integrationRequestParameterType.key(), integrationRequestParamName),
        String.format("method.request.%s.%s", methodRequestParameterType.key(), methodRequestParamName)
    );
  }
}
