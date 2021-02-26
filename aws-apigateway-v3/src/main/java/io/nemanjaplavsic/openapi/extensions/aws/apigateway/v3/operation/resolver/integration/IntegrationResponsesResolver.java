package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponse;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponseParameter;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponseTemplate;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponses;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterSource;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationResponsesExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.IntegrationResponseExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.parameter.IntegrationResponseParameterExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.parameter.IntegrationResponseParametersExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.template.IntegrationResponseTemplateExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.template.IntegrationResponseTemplatesExtension;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class IntegrationResponsesResolver implements IntegrationResolver<IntegrationResponsesExtension> {

  @Override
  public IntegrationResponsesExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    IntegrationResponsesExtension extension = new IntegrationResponsesExtension();

    // Responses
    final List<IntegrationResponseExtension> responseExtensions = Optional.ofNullable(operation.getResponses())
        .map(responses -> responses.entrySet().stream()
            .map(entry -> from(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList())
        )
        .orElse(new ArrayList<>());
    extension.responses(responseExtensions);

    // Integration Responses Annotation
    final List<IntegrationResponseExtension> integrationResponses = Optional.ofNullable(handlerMethod.getMethodAnnotation(IntegrationResponses.class))
        .map(annotation -> Arrays.stream(annotation.value())
            .map(this::from).collect(Collectors.toList()))
        .orElse(new ArrayList<>());
    extension.responses(integrationResponses);

    return extension;
  }

  public IntegrationResponseExtension from(String statusPattern, ApiResponse response) {
    final int code = "default".equals(statusPattern) ? 200 : Integer.parseInt(statusPattern);

    final List<IntegrationResponseParameterExtension> parameters = Optional.ofNullable(response.getHeaders())
        .map(headers -> headers.entrySet().stream()
            .map(entry -> resolveParameter(entry.getKey(), entry.getValue()))
            .filter(Objects::nonNull)
            .collect(Collectors.toList()))
        .orElse(new ArrayList<>());

    final List<IntegrationResponseTemplateExtension> templates = Optional.ofNullable(response.getContent())
        .map(content -> content.keySet()
            .stream()
            .map(mediaType -> new IntegrationResponseTemplateExtension(mediaType, null))
            .collect(Collectors.toList()))
        .orElse(new ArrayList<>());


    return new IntegrationResponseExtension(statusPattern, code)
        .parameters(new IntegrationResponseParametersExtension(parameters))
        .templates(new IntegrationResponseTemplatesExtension(templates));
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

  public IntegrationResponseParameterExtension resolveParameter(IntegrationResponseParameter parameter) {
    return new IntegrationResponseParameterExtension(parameter.source(), parameter.methodHeaderName())
        .integrationParameterType(parameter.integrationParameterType())
        .integrationParameterName(parameter.integrationParameterName())
        .staticValue(parameter.staticValue());
  }

  public IntegrationResponseParameterExtension resolveParameter(String name, Header header) {
    return new IntegrationResponseParameterExtension(IntegrationResponseParameterSource.INTEGRATION, name)
        .integrationParameterType(IntegrationResponseParameterType.HEADER)
        .integrationParameterName(name);
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
