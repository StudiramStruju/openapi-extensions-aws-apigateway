package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationRequestParameter;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationRequestParameters;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.RequestParameterSource;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationRequestParametersExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.request.IntegrationRequestParameterExtension;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.web.method.HandlerMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class IntegrationRequestParametersResolver implements IntegrationResolver<IntegrationRequestParametersExtension> {

  @Override
  public IntegrationRequestParametersExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    IntegrationRequestParametersExtension extension = new IntegrationRequestParametersExtension();

    // Resolve Global and Method parameters
    final List<IntegrationRequestParameterExtension> parameters = Optional.ofNullable(operation.getParameters())
        .map(params -> params
            .stream()
            .map(this::from)
            .collect(Collectors.toList()))
        .orElse(new ArrayList<>());
    extension.parameters(parameters);

    // Resolve Annotation parameters
    final List<IntegrationRequestParameterExtension> annotationParameters = Optional.ofNullable(handlerMethod.getMethodAnnotation(IntegrationRequestParameters.class))
        .map(params -> Arrays.stream(params.value())
            .map(this::from)
            .filter(Objects::nonNull)
            .collect(Collectors.toList()))
        .orElse(new ArrayList<>());
    extension.parameters(annotationParameters);

    return extension;
  }

  public IntegrationRequestParameterExtension from(IntegrationRequestParameter parameter) {
    return new IntegrationRequestParameterExtension(
        parameter.source(),
        parameter.integrationParameterType(),
        parameter.integrationParameterName(),
        parameter.methodParameterType(),
        parameter.methodParameterName(),
        parameter.contextVariableName(),
        parameter.stageVariableName(),
        parameter.staticValueName()
    );
  }

  public IntegrationRequestParameterExtension from(Parameter parameter) {
    return new IntegrationRequestParameterExtension(
        RequestParameterSource.METHOD,
        IntegrationRequestParameterType.valueOf(parameter.getIn().toUpperCase()),
        parameter.getName()
    );
  }

}
