package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParamType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StageVariableParamVendorExtension extends RequestParamVendorExtension {

  public StageVariableParamVendorExtension(IntegrationRequestParamType integrationRequestParamType,
                                           String integrationRequestParamName,
                                           String stageVariableName) {
    super(
        String.format("integration.request.%s.%s", integrationRequestParamType.key(), integrationRequestParamName),
        String.format("stageVariables.%s", stageVariableName)
    );
  }
}
