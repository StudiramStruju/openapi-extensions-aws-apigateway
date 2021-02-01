package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponse;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseParameterVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseParametersVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseTemplatesVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseVendorExtension;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResponseMessage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseResolver {

  public ResponseVendorExtension resolve(ApiResponse resolvedApiResponse) {
    final ResponseVendorExtension responseVendorExtension = new ResponseVendorExtension(String.valueOf(resolvedApiResponse.code()))
        .statusCode(resolvedApiResponse.code())
        .responseTemplates(new ResponseTemplatesVendorExtension());

    if (resolvedApiResponse.responseHeaders().length > 0) {
      final List<ResponseParameterVendorExtension> responseParams = Arrays.stream(resolvedApiResponse.responseHeaders())
          .map(header -> new ResponseParameterVendorExtension(
              header.name(),
              IntegrationResponseParameterType.HEADER,
              header.name()
          )).collect(Collectors.toList());
      responseVendorExtension.responseParameters(new ResponseParametersVendorExtension(responseParams));
    }
    return responseVendorExtension;
  }

  public ResponseVendorExtension resolve(IntegrationResponse annotation) {

    return null;
  }

  public ResponseVendorExtension resolve(ResponseMessage responseMessage) {
    return null;
  }

  public ResponseVendorExtension resolve(ApiResponse apiResponse, ResponseMessage responseMessage) {
    return null;
  }

  public ResponseVendorExtension resolve(ApiResponse apiResponse, IntegrationResponse integrationResponseAnnotation) {
    return null;
  }

  public ResponseVendorExtension resolve(ApiResponse resolvedApiResponse, IntegrationResponse annotation, ResponseMessage responseMessage) {
    return null;
  }

  public ResponseVendorExtension resolve(ResponseMessage responseMessage, IntegrationResponse integrationResponse) {
    return null;
  }
}
