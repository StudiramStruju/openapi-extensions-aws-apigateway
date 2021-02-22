package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationRequestParametersExtension;
import io.swagger.v3.oas.models.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Slf4j
@Component
public class IntegrationRequestParametersResolver implements IntegrationResolver<IntegrationRequestParametersExtension> {

  @Override
  public IntegrationRequestParametersExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    IntegrationRequestParametersExtension extension = new IntegrationRequestParametersExtension();

    // todo find a way to include context, stage and static variables

    // TODO: 2/22/21 fix this

    // Resolve Global Parameters
//    final List<IntegrationRequestParameterExtension> globalParameters = operation.getGlobalOperationParameters()
//        .stream().map(this::from)
//        .filter(Objects::nonNull)
//        .collect(Collectors.toList());
//    extension.parameters(globalParameters);
//
//    // Resolve Method Parameters
//    final List<IntegrationRequestParameterExtension> methodParameters = operation.getParameters()
//        .stream().map(parameter -> from(parameter, operation.getName()))
//        .filter(Objects::nonNull)
//        .collect(Collectors.toList());
//    extension.parameters(methodParameters);
//
//    // Resolve Annotation parameters
//    final List<IntegrationRequestParameterExtension> annotationParameters = operation.findAnnotation(IntegrationRequestParameters.class)
//        .transform(parameters -> Arrays.stream(parameters.value()).map(this::from)
//            .filter(Objects::nonNull)
//            .collect(Collectors.toList()))
//        .or(new ArrayList<>());
//    extension.parameters(annotationParameters);

    return extension;
  }

//  public IntegrationRequestParameterExtension from(Parameter parameter) {
//    final String name = Objects.requireNonNull(parameter.getName(),
//        String.format("Global parameter does not have 'name' defined! %s", parameter.toString()));
//
//    final String paramType = Objects.requireNonNull(parameter.getParamType(),
//        String.format("Global parameter does not have 'paramType' defined! %s", parameter.toString())).toLowerCase();
//
//    return new IntegrationRequestParameterExtension(RequestParameterSource.METHOD, getIntegrationTypeFromParameterType(paramType), name)
//        .methodParameterType(getMethodTypeFromParameterType(paramType))
//        .methodParameterName(name);
//  }
//
//  public IntegrationRequestParameterExtension from(ResolvedMethodParameter methodParameter, String name) {
//    // TODO: 2/12/21 Check for @ApiIgnoreProperty and how to handle that
//
//    if (methodParameter.findAnnotation(PathVariable.class).isPresent()) {
//      final PathVariable pathVariable = methodParameter.findAnnotation(PathVariable.class).get();
//      final String parameterName = getFirstNonEmptyValue(pathVariable.value(), pathVariable.name(), methodParameter.defaultName().orNull());
//      if (Objects.isNull(parameterName)) {
//        log.warn("Could not resolve PathVariable Method parameter for method '{}'! No name defined!", name);
//        return null;
//      }
//
//      return new IntegrationRequestParameterExtension(RequestParameterSource.METHOD, IntegrationRequestParameterType.PATH, name)
//          .methodParameterType(MethodRequestParameterType.PATH)
//          .methodParameterName(parameterName);
//    }
//
//    if (methodParameter.findAnnotation(RequestParam.class).isPresent()) {
//      final RequestParam requestParam = methodParameter.findAnnotation(RequestParam.class).get();
//      final String parameterName = getFirstNonEmptyValue(requestParam.value(), requestParam.name(), methodParameter.defaultName().orNull());
//      if (Objects.isNull(parameterName)) {
//        log.warn("Could not resolve RequestParam Method parameter for method '{}'! No name defined!", name);
//        return null;
//      }
//
//      return new IntegrationRequestParameterExtension(RequestParameterSource.METHOD, IntegrationRequestParameterType.QUERY, name)
//          .methodParameterType(MethodRequestParameterType.QUERY)
//          .methodParameterName(parameterName);
//    }
//
//    if (methodParameter.findAnnotation(RequestHeader.class).isPresent()) {
//      final RequestHeader requestHeader = methodParameter.findAnnotation(RequestHeader.class).get();
//      final String parameterName = getFirstNonEmptyValue(requestHeader.value(), requestHeader.name(), methodParameter.defaultName().orNull());
//      if (Objects.isNull(parameterName)) {
//        log.warn("Could not resolve RequestHeader Method parameter for method '{}'! No name defined!", name);
//        return null;
//      }
//
//      return new IntegrationRequestParameterExtension(RequestParameterSource.METHOD, IntegrationRequestParameterType.HEADER, name)
//          .methodParameterType(MethodRequestParameterType.HEADER)
//          .methodParameterName(parameterName);
//    }
//    return null;
//  }
//
//  public IntegrationRequestParameterExtension from(IntegrationRequestParameter parameter) {
//    return new IntegrationRequestParameterExtension(
//        parameter.source(),
//        parameter.integrationParameterType(),
//        parameter.integrationParameterName(),
//        parameter.methodParameterType(),
//        parameter.methodParameterName(),
//        parameter.contextVariableName(),
//        parameter.stageVariableName(),
//        parameter.staticValueName()
//    );
//  }
//
//  public RequestParameterSource getSourceFromType(String paramType) {
//    switch (paramType) {
//      case "header":
//      case "query":
//      case "path":
//        return RequestParameterSource.METHOD;
//      default:
//        throw new ApiGatewayIntegrationExtensionPropertyResolverException(
//            String.format("Cloud not resolve Global parameter '%s' parameter type! Allowed values are ['header', 'query', 'path']", paramType));
//    }
//  }
//
//  public IntegrationRequestParameterType getIntegrationTypeFromParameterType(String paramType) {
//    switch (paramType) {
//      case "header":
//        return IntegrationRequestParameterType.HEADER;
//      case "query":
//        return IntegrationRequestParameterType.QUERY;
//      case "path":
//        return IntegrationRequestParameterType.PATH;
//      default:
//        throw new ApiGatewayIntegrationExtensionPropertyResolverException(
//            String.format("Cloud not resolve Global parameter '%s' parameter type! Allowed values are ['header', 'query', 'path']", paramType));
//    }
//  }
//
//  public MethodRequestParameterType getMethodTypeFromParameterType(String paramType) {
//    switch (paramType) {
//      case "header":
//        return MethodRequestParameterType.HEADER;
//      case "query":
//        return MethodRequestParameterType.QUERY;
//      case "path":
//        return MethodRequestParameterType.PATH;
//      default:
//        throw new ApiGatewayIntegrationExtensionPropertyResolverException(
//            String.format("Cloud not resolve Global parameter '%s' parameter type! Allowed values are ['header', 'query', 'path']", paramType));
//    }
//  }

}
