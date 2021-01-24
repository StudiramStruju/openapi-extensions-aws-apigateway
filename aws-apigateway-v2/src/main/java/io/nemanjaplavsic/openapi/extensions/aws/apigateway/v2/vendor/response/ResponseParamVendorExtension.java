package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.StringVendorExtension;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponseParamVendorExtension extends StringVendorExtension {

  public ResponseParamVendorExtension(String methodHeaderName,
                                      IntegrationResponseParameterType integrationResponseParamType,
                                      String integrationResponseParamName) {
    super(
        String.format("method.response.header.%s", methodHeaderName),
        String.format("integration.response.%s.%s", integrationResponseParamType.key(), integrationResponseParamName)
    );
  }
}
