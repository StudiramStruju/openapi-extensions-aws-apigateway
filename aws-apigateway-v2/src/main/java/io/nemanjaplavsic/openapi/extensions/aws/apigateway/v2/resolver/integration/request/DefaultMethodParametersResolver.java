package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationRequestParameter;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.MethodRequestParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.exception.ApiGatewayIntegrationExtensionPropertyResolverException;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.ContextParamVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.MethodRequestParamVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.RequestParamVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.StageVariableParamVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.StaticValueParamVendorExtension;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.service.ResolvedMethodParameter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
//@ConditionalOnMissingBean(value = MethodParametersResolver.class)
public class DefaultMethodParametersResolver implements MethodParametersResolver {

  public static String extractFirstNonEmptyValue(String... values) {
    return Stream.of(values)
        .filter(v -> !StringUtils.isEmpty(v))
        .findFirst()
        .orElseThrow(() -> new RuntimeException(String.format("Could not extract non null value from '%s'", Arrays.toString(values))));
  }

  @Override
  public List<RequestParamVendorExtension> resolve(List<ResolvedMethodParameter> methodParameters, @Nullable IntegrationRequestParameter[] annotationParameters) {
    return methodParameters.stream()
        .map(methodParameter -> resolveMethodParameter(methodParameter, annotationParameters))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  @Nullable
  public RequestParamVendorExtension resolveMethodParameter(ResolvedMethodParameter methodParameter,
                                                            @Nullable IntegrationRequestParameter[] methodAnnotationParameters) {

    IntegrationRequestParameter parameterAnnotation = methodParameter.findAnnotation(IntegrationRequestParameter.class).orNull();

    if (methodParameter.hasParameterAnnotation(PathVariable.class)) {
      final PathVariable pathVariable = methodParameter.findAnnotation(PathVariable.class).get();
      final String parameterName = extractFirstNonEmptyValue(pathVariable.value(), pathVariable.name(), methodParameter.defaultName().orNull());
      return resolveMethodParameter(IntegrationRequestParameterType.PATH, parameterName, parameterAnnotation, methodAnnotationParameters);
    }

    if (methodParameter.hasParameterAnnotation(RequestParam.class)) {
      final RequestParam requestParam = methodParameter.findAnnotation(RequestParam.class).get();
      final String parameterName = extractFirstNonEmptyValue(requestParam.value(), requestParam.name(), methodParameter.defaultName().orNull());
      return resolveMethodParameter(IntegrationRequestParameterType.QUERY, parameterName, parameterAnnotation, methodAnnotationParameters);
    }

    if (methodParameter.hasParameterAnnotation(RequestHeader.class)) {
      final RequestHeader requestHeader = methodParameter.findAnnotation(RequestHeader.class).get();
      final String parameterName = extractFirstNonEmptyValue(requestHeader.value(), requestHeader.name(), methodParameter.defaultName().orNull());
      return resolveMethodParameter(IntegrationRequestParameterType.HEADER, parameterName, parameterAnnotation, methodAnnotationParameters);
    }

    return null;
  }

  public RequestParamVendorExtension resolveMethodParameter(IntegrationRequestParameterType paramType,
                                                            String parameterName,
                                                            @Nullable IntegrationRequestParameter parameterAnnotation,
                                                            @Nullable IntegrationRequestParameter[] methodAnnotationParameters) {

    if (Objects.nonNull(parameterAnnotation)) {
      return createFromSource(parameterAnnotation, paramType, parameterName);
    } else if (Objects.nonNull(methodAnnotationParameters)) {

      final IntegrationRequestParameter methodAnnotation = Arrays.stream(methodAnnotationParameters)
          .filter(param -> parameterName.equals(param.integrationRequestParamName()))
          .findFirst()
          .orElse(null);

      if (Objects.nonNull(methodAnnotation)) {
        return createFromSource(methodAnnotation, paramType, parameterName);
      }

    }
    // If no annotations present fallback to default mapping
    return new MethodRequestParamVendorExtension(
        paramType, parameterName,
        MethodRequestParameterType.matching(paramType), parameterName
    );

  }

  public RequestParamVendorExtension createFromSource(IntegrationRequestParameter annotation, IntegrationRequestParameterType paramType, String parameterName) {
    switch (annotation.source()) {
      case METHOD:
        return createFromMethodSource(paramType, parameterName, annotation);
      case CONTEXT:
        return createFromContextSource(paramType, parameterName, annotation);
      case STAGE:
        return createFromStageSource(paramType, parameterName, annotation);
      case STATIC:
        return createFromStaticSource(paramType, parameterName, annotation);
      default:
        throw new ApiGatewayIntegrationExtensionPropertyResolverException(
            "@RequestParameter(source = ?) annotation source attribute must be defined!"
        );
    }
  }

  public RequestParamVendorExtension createFromMethodSource(IntegrationRequestParameterType type, String parameterName, IntegrationRequestParameter annotation) {
    if (MethodRequestParameterType.NONE.equals(annotation.methodRequestParamType())) {
      throw new ApiGatewayIntegrationExtensionPropertyResolverException(
          String.format("Failed to create '%s' request parameter! methodRequestParamType cannot be NONE when using METHOD source!", parameterName)
      );
    }
    return new MethodRequestParamVendorExtension(
        type,
        parameterName,
        annotation.methodRequestParamType(),
        StringUtils.hasText(annotation.methodParameterName()) ? annotation.methodParameterName() : parameterName
    );
  }

  public RequestParamVendorExtension createFromContextSource(IntegrationRequestParameterType type, String parameterName, IntegrationRequestParameter annotation) {
    if (!StringUtils.hasText(annotation.contextVariableName())) {
      throw new ApiGatewayIntegrationExtensionPropertyResolverException(
          String.format("Failed to create '%s' request parameter! contextVariableName cannot be empty when using CONTEXT source!", parameterName)
      );
    }
    return new ContextParamVendorExtension(
        type,
        parameterName,
        annotation.contextVariableName()
    );
  }

  public RequestParamVendorExtension createFromStageSource(IntegrationRequestParameterType type, String parameterName, IntegrationRequestParameter annotation) {
    if (!StringUtils.hasText(annotation.stageVariableName())) {
      throw new ApiGatewayIntegrationExtensionPropertyResolverException(
          String.format("Failed to create '%s' request parameter! stageVariableName cannot be empty when using STAGE source!", parameterName)
      );
    }
    return new StageVariableParamVendorExtension(
        type,
        parameterName,
        annotation.stageVariableName()
    );
  }

  public RequestParamVendorExtension createFromStaticSource(IntegrationRequestParameterType type, String parameterName, IntegrationRequestParameter annotation) {
    if (!StringUtils.hasText(annotation.staticValueName())) {
      throw new ApiGatewayIntegrationExtensionPropertyResolverException(
          String.format("Failed to create '%s' request parameter! staticValueName cannot be empty when using STATIC source!", parameterName)
      );
    }
    return new StaticValueParamVendorExtension(
        type,
        parameterName,
        annotation.stageVariableName()
    );
  }
}
