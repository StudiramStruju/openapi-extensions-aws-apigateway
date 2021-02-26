package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponse;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponseParameter;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponseTemplate;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponses;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterSource;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationResponsesExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.response.IntegrationResponseExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.response.IntegrationResponseParameterExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.response.IntegrationResponseParametersExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.response.IntegrationResponseTemplateExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.response.IntegrationResponseTemplatesExtension;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.lang.Nullable;
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
    // Resolve parameters
    final List<IntegrationResponseParameterExtension> parameters = message.getHeaders().entrySet().stream()
        .map(entry -> resolveParameterFromHeader(entry.getKey(), entry.getValue()))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    return new IntegrationResponseExtension(String.valueOf(message.getCode()), message.getCode())
        .parameters(new IntegrationResponseParametersExtension(parameters));
  }

  public IntegrationResponseExtension from(ApiResponse apiResponse) {
    final List<IntegrationResponseParameterExtension> parameters = Arrays.stream(apiResponse.responseHeaders())
        .map(this::resolveParameterFromHeader)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    return new IntegrationResponseExtension(String.valueOf(apiResponse.code()), apiResponse.code())
        .parameters(new IntegrationResponseParametersExtension(parameters));
  }

  public IntegrationResponseExtension from(IntegrationResponse integrationResponse) {
    final List<IntegrationResponseParameterExtension> parameters = Arrays.stream(integrationResponse.parameters().value())
        .map(this::resolveParameter)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    final List<IntegrationResponseTemplateExtension> templates = Arrays.stream(integrationResponse.templates().value())
        .map(this::resolveTemplates)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());


    return new IntegrationResponseExtension(integrationResponse.responseStatusPattern(), integrationResponse.statusCode())
        .contentHandling(integrationResponse.contentHandling())
        .parameters(new IntegrationResponseParametersExtension(parameters))
        .templates(new IntegrationResponseTemplatesExtension(templates));
  }

  @Nullable
  public IntegrationResponseParameterExtension resolveParameterFromHeader(String name, Header header) {
    final String headerName = getFirstNonEmptyValue(name, header.getName());
    if (Objects.isNull(headerName)) {
      return null;
    }
    return new IntegrationResponseParameterExtension(IntegrationResponseParameterSource.INTEGRATION, headerName)
        .integrationParameterType(IntegrationResponseParameterType.HEADER)
        .integrationParameterName(headerName);
  }

  @Nullable
  public IntegrationResponseParameterExtension resolveParameterFromHeader(ResponseHeader header) {
    final String headerName = getFirstNonEmptyValue(header.name());
    if (Objects.isNull(headerName)) {
      return null;
    }
    return new IntegrationResponseParameterExtension(IntegrationResponseParameterSource.INTEGRATION, headerName)
        .integrationParameterType(IntegrationResponseParameterType.HEADER)
        .integrationParameterName(headerName);
  }

  public IntegrationResponseParameterExtension resolveParameter(IntegrationResponseParameter parameter) {
    return new IntegrationResponseParameterExtension(parameter.source(), parameter.methodHeaderName())
        .integrationParameterType(parameter.integrationParameterType())
        .integrationParameterName(parameter.integrationParameterName())
        .staticValue(parameter.staticValue());
  }

  @Nullable
  public IntegrationResponseTemplateExtension resolveTemplates(IntegrationResponseTemplate templates) {
    try {
      return new IntegrationResponseTemplateExtension(templates.mediaType(), templates.template());
    } catch (InvalidMediaTypeException e) {
      return null;
    }
  }
}
