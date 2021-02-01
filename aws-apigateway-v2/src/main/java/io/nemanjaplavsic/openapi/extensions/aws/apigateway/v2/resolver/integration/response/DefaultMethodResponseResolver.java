package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponse;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponseParameter;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponseParameters;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponseTemplate;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponseTemplates;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.ContentHandlingVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseParameterVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseParametersVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseTemplateVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseTemplatesVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.StatusCodeVendorExtension;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ResponseHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultMethodResponseResolver implements MethodResponseResolver {

  @Override
  public List<ResponseVendorExtension> resolve(List<ApiResponse> apiResponses, List<IntegrationResponse> respons) {
    final List<ResponseVendorExtension> resolvedResponses = respons.stream().map(this::resolve).collect(Collectors.toList());
    final List<ResponseVendorExtension> resolvedApiResponses = respons.stream().map(this::resolve).collect(Collectors.toList());


    if (resolvedResponses.isEmpty()) {
      return resolvedApiResponses;
    } else if (resolvedApiResponses.isEmpty()) {
      return resolvedResponses;
    }
    return null;
  }

  public ResponseVendorExtension resolve(ApiResponse apiResponse, List<IntegrationResponse> integrationResponseAnnotations) {

    final Optional<IntegrationResponse> responseAnnotation = integrationResponseAnnotations.stream()
        .filter(response -> Pattern.matches(response.responseStatusPattern(), String.valueOf(apiResponse.code())))
        .findFirst();

    return responseAnnotation
        .map(response -> resolve(apiResponse, response))
        .orElseGet(() -> resolve(apiResponse));
  }

  public ResponseVendorExtension resolve(ApiResponse apiResponse, IntegrationResponse responsesAnnotation) {
    return null;
  }

  public ResponseVendorExtension resolve(IntegrationResponse integrationResponse) {
    final ResponseParametersVendorExtension responseParametersVendorExtension = resolveParameters(integrationResponse.responseParameters());
    final StatusCodeVendorExtension statusCodeVendorExtension = new StatusCodeVendorExtension(integrationResponse.statusCode());
    final ResponseTemplatesVendorExtension responseTemplatesVendorExtension = resolveTemplates(integrationResponse.templates());
    final ContentHandlingVendorExtension contentHandlingVendorExtension = new ContentHandlingVendorExtension(ContentHandling.NONE);

    return new ResponseVendorExtension(integrationResponse.responseStatusPattern(), List.of(
        responseParametersVendorExtension,
        statusCodeVendorExtension,
        responseTemplatesVendorExtension,
        contentHandlingVendorExtension
    ));
  }

  public ResponseParametersVendorExtension resolveParameters(IntegrationResponseParameters integrationResponseParameters) {
    final List<ResponseParameterVendorExtension> parameters = Arrays.stream(integrationResponseParameters.value())
        .map(this::resolveParameter)
        .collect(Collectors.toList());
    return new ResponseParametersVendorExtension(parameters);
  }

  public ResponseParameterVendorExtension resolveParameter(IntegrationResponseParameter responseParameter) {
    return new ResponseParameterVendorExtension(
        responseParameter.methodHeaderName(),
        responseParameter.integrationResponseParamType(),
        responseParameter.integrationResponseParamName()
    );
  }

  public ResponseTemplatesVendorExtension resolveTemplates(IntegrationResponseTemplates responseTemplates) {
    final List<ResponseTemplateVendorExtension> templates = Arrays.stream(responseTemplates.value())
        .map(this::resolveTemplate)
        .collect(Collectors.toList());
    return new ResponseTemplatesVendorExtension(templates);
  }

  public ResponseTemplateVendorExtension resolveTemplate(IntegrationResponseTemplate responseTemplate) {
    return new ResponseTemplateVendorExtension(
        responseTemplate.mediaType(),
        responseTemplate.template()
    );
  }

  public ResponseVendorExtension resolve(ApiResponse response) {
    final ResponseParametersVendorExtension responseParametersVendorExtension = resolveParameters(response.responseHeaders());
    final StatusCodeVendorExtension statusCodeVendorExtension = new StatusCodeVendorExtension(response.code());
    final ResponseTemplatesVendorExtension responseTemplatesVendorExtension = new ResponseTemplatesVendorExtension(List.of(
        new ResponseTemplateVendorExtension(MediaType.APPLICATION_JSON, ResponseTemplateVendorExtension.DEFAULT_TEMPLATE))
    );
    final ContentHandlingVendorExtension contentHandlingVendorExtension = new ContentHandlingVendorExtension(ContentHandling.NONE);

    return new ResponseVendorExtension(
        String.valueOf(response.code()), List.of(
        responseParametersVendorExtension,
        statusCodeVendorExtension,
        responseTemplatesVendorExtension,
        contentHandlingVendorExtension
    ));
  }

  public ResponseParametersVendorExtension resolveParameters(ResponseHeader[] responseHeaders) {
    final List<ResponseParameterVendorExtension> parameters = Arrays.stream(responseHeaders)
        .map(this::resolveParameter)
        .collect(Collectors.toList());
    return new ResponseParametersVendorExtension(parameters);
  }

  public ResponseParameterVendorExtension resolveParameter(ResponseHeader responseHeader) {
    return new ResponseParameterVendorExtension(
        responseHeader.name(),
        IntegrationResponseParameterType.HEADER,
        responseHeader.name()
    );
  }
}
