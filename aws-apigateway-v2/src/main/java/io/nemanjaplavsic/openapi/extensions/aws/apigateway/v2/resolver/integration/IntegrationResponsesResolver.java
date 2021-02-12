package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponse;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponseParameter;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponseTemplate;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponses;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationResponseExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationResponseParameterExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationResponseParametersExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationResponseTemplateExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationResponseTemplatesExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationResponsesExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationStatusCodeExtension;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class IntegrationResponsesResolver implements IntegrationResolver<IntegrationResponsesExtension> {

  @Override
  public IntegrationResponsesExtension resolve(OperationContext context) {
    IntegrationResponsesExtension extension = new IntegrationResponsesExtension();

    // Resolve Global Responses
    final List<IntegrationResponseExtension> globalResponses = context.getGlobalResponseMessages(context.httpMethod().name())
        .stream().map(this::from).collect(Collectors.toList());
    extension.responses(globalResponses);

    // Api Responses Annotation
    final List<IntegrationResponseExtension> apiResponses = context.findAnnotation(ApiResponses.class)
        .transform(apiResponse -> Arrays.stream(apiResponse.value()).map(this::from).collect(Collectors.toList()))
        .or(new ArrayList<>());
    extension.responses(apiResponses);

    // Api Response Annotation
    final IntegrationResponseExtension singleResponse = context.findAnnotation(ApiResponse.class)
        .transform(this::from).orNull();
    if (Objects.nonNull(singleResponse)) {
      extension.response(singleResponse);
    }

    // Integration Responses Annotation
    final List<IntegrationResponseExtension> integrationResponses = context.findAnnotation(IntegrationResponses.class)
        .transform(annotation -> Arrays.stream(annotation.value()).map(this::from).collect(Collectors.toList()))
        .or(new ArrayList<>());
    extension.responses(integrationResponses);

    return extension;
  }

  public IntegrationResponseExtension from(ResponseMessage message) {
    IntegrationResponseExtension extension = new IntegrationResponseExtension();

    // Resolve parameters
    final List<IntegrationResponseParameterExtension> parameters = message.getHeaders().entrySet().stream()
        .map(entry -> resolveParameterFromHeader(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());

    return extension.statusCode(message.getCode())
        .responseStatusPattern(String.valueOf(message.getCode()))
        .parameters(new IntegrationResponseParametersExtension(parameters));
  }

  public IntegrationResponseExtension from(ApiResponse apiResponse) {
    IntegrationResponseExtension extension = new IntegrationResponseExtension();

    final List<IntegrationResponseParameterExtension> parameters = Arrays.stream(apiResponse.responseHeaders())
        .map(this::resolveParameterFromHeader)
        .collect(Collectors.toList());

    return extension.responseStatusPattern(String.valueOf(apiResponse.code()))
        .statusCode(apiResponse.code())
        .parameters(new IntegrationResponseParametersExtension(parameters));
  }

  public IntegrationResponseExtension from(IntegrationResponse integrationResponse) {
    final List<IntegrationResponseParameterExtension> parameters = Arrays.stream(integrationResponse.parameters().value())
        .map(this::resolveParameter)
        .collect(Collectors.toList());

    final List<IntegrationResponseTemplateExtension> templates = Arrays.stream(integrationResponse.templates().value())
        .map(this::resolveTemplates)
        .collect(Collectors.toList());


    return IntegrationResponseExtension.builder()
        .responseStatusPattern(integrationResponse.responseStatusPattern())
        .statusCode(new IntegrationStatusCodeExtension(integrationResponse.statusCode()))
        .contentHandling(integrationResponse.contentHandling())
        .parameters(new IntegrationResponseParametersExtension(parameters))
        .templates(new IntegrationResponseTemplatesExtension(templates))
        .build();
  }

  public IntegrationResponseParameterExtension resolveParameterFromHeader(String name, Header header) {
    final String headerName = getFirstNonEmptyValue(name, header.getName());
    return IntegrationResponseParameterExtension.builder()
        .integrationParameterType(IntegrationResponseParameterType.HEADER)
        .integrationParameterName(headerName)
        .methodHeaderName(headerName)
        .build();
  }

  public IntegrationResponseParameterExtension resolveParameterFromHeader(ResponseHeader header) {
    final String headerName = getFirstNonEmptyValue(header.name());
    return IntegrationResponseParameterExtension.builder()
        .integrationParameterType(IntegrationResponseParameterType.HEADER)
        .integrationParameterName(headerName)
        .methodHeaderName(headerName)
        .build();
  }

  public IntegrationResponseParameterExtension resolveParameter(IntegrationResponseParameter parameter) {
    return IntegrationResponseParameterExtension.builder()
        .integrationParameterType(parameter.integrationParameterType())
        .integrationParameterName(parameter.integrationParameterName())
        .methodHeaderName(parameter.methodHeaderName())
        .build();
  }

  public IntegrationResponseTemplateExtension resolveTemplates(IntegrationResponseTemplate templates) {
    return IntegrationResponseTemplateExtension.builder()
        .mediaType(MediaType.valueOf(templates.mediaType()))
        .template(templates.template())
        .build();
  }
}
