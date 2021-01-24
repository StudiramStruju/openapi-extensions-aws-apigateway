package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParamType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.MethodRequestParamType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.exception.ApiGatewayIntegrationExtensionPropertyResolverException;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.MethodRequestParamVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.RequestParamVendorExtension;
import org.springframework.stereotype.Component;
import springfox.documentation.service.Parameter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
//@ConditionalOnMissingBean(value = GlobalOperationParameterApiGatewayIntegrationExtensionPropertyResolver.class)
public class DefaultGlobalOperationParameterApiGatewayIntegrationExtensionPropertyResolver implements GlobalOperationParameterApiGatewayIntegrationExtensionPropertyResolver {

  @Override
  public List<RequestParamVendorExtension> resolve(List<Parameter> globalParameters) {
    return globalParameters.stream()
        .map(this::resolveParameter)
        .collect(Collectors.toList());
  }

  public MethodRequestParamVendorExtension resolveParameter(Parameter parameter) {
    final String name = Objects.requireNonNull(parameter.getName(),
        String.format("Global parameter does not have 'name' defined! %s", parameter.toString()));

    final String paramType = Objects.requireNonNull(parameter.getParamType(),
        String.format("Global parameter does not have 'paramType' defined! %s", parameter.toString())).toLowerCase();

    return new MethodRequestParamVendorExtension(
        getIntegrationTypeFromParameterType(paramType),
        name,
        getMethodTypeFromParameterType(paramType),
        name
    );
  }

  public IntegrationRequestParamType getIntegrationTypeFromParameterType(String paramType) {
    switch (paramType) {
      case "header":
        return IntegrationRequestParamType.HEADER;
      case "query":
        return IntegrationRequestParamType.QUERY;
      case "path":
        return IntegrationRequestParamType.PATH;
      default:
        throw new ApiGatewayIntegrationExtensionPropertyResolverException(
            String.format("Cloud not resolve Global parameter '%s' parameter type! Allowed values are ['header', 'query', 'path']", paramType));
    }
  }

  public MethodRequestParamType getMethodTypeFromParameterType(String paramType) {
    switch (paramType) {
      case "header":
        return MethodRequestParamType.HEADER;
      case "query":
        return MethodRequestParamType.QUERY;
      case "path":
        return MethodRequestParamType.PATH;
      default:
        throw new ApiGatewayIntegrationExtensionPropertyResolverException(
            String.format("Cloud not resolve Global parameter '%s' parameter type! Allowed values are ['header', 'query', 'path']", paramType));
    }
  }
}
