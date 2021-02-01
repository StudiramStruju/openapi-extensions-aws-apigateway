package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParameterType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-mapping-template-reference.html#context-variable-reference
 * Unlike in templates, context is references here without $ sign.
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContextParamVendorExtension extends RequestParamVendorExtension {

  public ContextParamVendorExtension(IntegrationRequestParameterType integrationRequestParameterType,
                                     String integrationRequestParamName,
                                     String contextVariableName) {
    super(
        String.format("integration.request.%s.%s", integrationRequestParameterType.key(), integrationRequestParamName),
        String.format("context.%s", contextVariableName)
    );
  }
}
