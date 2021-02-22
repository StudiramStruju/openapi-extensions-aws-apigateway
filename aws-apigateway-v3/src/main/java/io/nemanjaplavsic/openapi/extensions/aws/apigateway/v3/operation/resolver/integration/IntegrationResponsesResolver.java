package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationResponsesExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Component
public class IntegrationResponsesResolver implements IntegrationResolver<IntegrationResponsesExtension> {

  @Override
  public IntegrationResponsesExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    IntegrationResponsesExtension extension = new IntegrationResponsesExtension();

    // TODO: 2/22/21 fix this

//    // Resolve Global Responses
//    final List<IntegrationResponseExtension> globalResponses = operation.getGlobalResponseMessages(operation.httpMethod().name())
//        .stream().map(this::from).collect(Collectors.toList());
//    extension.responses(globalResponses);
//
//    // Api Responses Annotation
//    final List<IntegrationResponseExtension> apiResponses = operation.findAnnotation(ApiResponses.class)
//        .transform(apiResponse -> Arrays.stream(apiResponse.value()).map(this::from).collect(Collectors.toList()))
//        .or(new ArrayList<>());
//    extension.responses(apiResponses);
//
//    // Api Response Annotation
//    final IntegrationResponseExtension singleResponse = operation.findAnnotation(ApiResponse.class)
//        .transform(this::from).orNull();
//    if (Objects.nonNull(singleResponse)) {
//      extension.response(singleResponse);
//    }
//
//    // Integration Responses Annotation
//    final List<IntegrationResponseExtension> integrationResponses = operation.findAnnotation(IntegrationResponses.class)
//        .transform(annotation -> Arrays.stream(annotation.value()).map(this::from).collect(Collectors.toList()))
//        .or(new ArrayList<>());
//    extension.responses(integrationResponses);

    return extension;
  }

//  public IntegrationResponseExtension from(ResponseMessage message) {
//    // Resolve parameters
//    final List<IntegrationResponseParameterExtension> parameters = message.getHeaders().entrySet().stream()
//        .map(entry -> resolveParameterFromHeader(entry.getKey(), entry.getValue()))
//        .filter(Objects::nonNull)
//        .collect(Collectors.toList());
//
//    return new IntegrationResponseExtension(String.valueOf(message.getCode()), message.getCode())
//        .parameters(new IntegrationResponseParametersExtension(parameters));
//  }
//
//  public IntegrationResponseExtension from(ApiResponse apiResponse) {
//    final List<IntegrationResponseParameterExtension> parameters = Arrays.stream(apiResponse.responseHeaders())
//        .map(this::resolveParameterFromHeader)
//        .filter(Objects::nonNull)
//        .collect(Collectors.toList());
//
//    return new IntegrationResponseExtension(String.valueOf(apiResponse.code()), apiResponse.code())
//        .parameters(new IntegrationResponseParametersExtension(parameters));
//  }
//
//  public IntegrationResponseExtension from(IntegrationResponse integrationResponse) {
//    final List<IntegrationResponseParameterExtension> parameters = Arrays.stream(integrationResponse.parameters().value())
//        .map(this::resolveParameter)
//        .filter(Objects::nonNull)
//        .collect(Collectors.toList());
//
//    final List<IntegrationResponseTemplateExtension> templates = Arrays.stream(integrationResponse.templates().value())
//        .map(this::resolveTemplates)
//        .filter(Objects::nonNull)
//        .collect(Collectors.toList());
//
//
//    return new IntegrationResponseExtension(integrationResponse.responseStatusPattern(), integrationResponse.statusCode())
//        .contentHandling(integrationResponse.contentHandling())
//        .parameters(new IntegrationResponseParametersExtension(parameters))
//        .templates(new IntegrationResponseTemplatesExtension(templates));
//  }
//
//  @Nullable
//  public IntegrationResponseParameterExtension resolveParameterFromHeader(String name, Header header) {
//    final String headerName = getFirstNonEmptyValue(name, header.getName());
//    if (Objects.isNull(headerName)) {
//      return null;
//    }
//    return new IntegrationResponseParameterExtension(ResponseParameterSource.INTEGRATION, headerName)
//        .integrationParameterType(IntegrationResponseParameterType.HEADER)
//        .integrationParameterName(headerName);
//  }
//
//  @Nullable
//  public IntegrationResponseParameterExtension resolveParameterFromHeader(ResponseHeader header) {
//    final String headerName = getFirstNonEmptyValue(header.name());
//    if (Objects.isNull(headerName)) {
//      return null;
//    }
//    return new IntegrationResponseParameterExtension(ResponseParameterSource.INTEGRATION, headerName)
//        .integrationParameterType(IntegrationResponseParameterType.HEADER)
//        .integrationParameterName(headerName);
//  }
//
//  public IntegrationResponseParameterExtension resolveParameter(IntegrationResponseParameter parameter) {
//    return new IntegrationResponseParameterExtension(parameter.source(), parameter.methodHeaderName())
//        .integrationParameterType(parameter.integrationParameterType())
//        .integrationParameterName(parameter.integrationParameterName())
//        .staticValue(parameter.staticValue());
//  }
//
//  @Nullable
//  public IntegrationResponseTemplateExtension resolveTemplates(IntegrationResponseTemplate templates) {
//    try {
//      return new IntegrationResponseTemplateExtension(templates.mediaType(), templates.template());
//    } catch (InvalidMediaTypeException e) {
//      return null;
//    }
//  }
}
