package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseParameterVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseParametersVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseTemplatesVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.StatusCodeVendorExtension;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResponseMessage;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultGlobalResponseResolver implements GlobalResponseResolver {

  @Override
  public List<ResponseVendorExtension> resolve(List<ResponseMessage> globalResponseMessages) {
    return globalResponseMessages.stream().map(this::resolve).collect(Collectors.toList());
  }

  public ResponseVendorExtension resolve(ResponseMessage globalResponseMessage) {

    final List<ResponseParameterVendorExtension> responseParams = globalResponseMessage.getHeaders().keySet()
        .stream()
        .map(header -> new ResponseParameterVendorExtension(header, IntegrationResponseParameterType.HEADER, header))
        .collect(Collectors.toList());

    final ResponseParametersVendorExtension responseParametersVendorExtension = new ResponseParametersVendorExtension(responseParams);
    final StatusCodeVendorExtension statusCodeVendorExtension = new StatusCodeVendorExtension(globalResponseMessage.getCode());
    final ResponseTemplatesVendorExtension responseTemplatesVendorExtension = new ResponseTemplatesVendorExtension(null);
    // final ContentHandlingVendorExtension contentHandlingVendorExtension =  new ContentHandlingVendorExtension(ContentHandling.NONE);

    return new ResponseVendorExtension(String.valueOf(globalResponseMessage.getCode()), List.of(
        responseParametersVendorExtension,
        statusCodeVendorExtension,
        responseTemplatesVendorExtension
    ));

  }

}
