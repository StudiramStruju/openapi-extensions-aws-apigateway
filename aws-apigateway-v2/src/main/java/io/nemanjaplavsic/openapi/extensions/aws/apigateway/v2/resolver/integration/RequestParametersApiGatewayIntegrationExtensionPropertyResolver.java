package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.RequestParameter;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.request.GlobalOperationParameterApiGatewayIntegrationExtensionPropertyResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.request.MethodParametersApiGatewayExtensionPropertyResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.RequestParametersVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.RequestParamVendorExtension;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestParametersApiGatewayIntegrationExtensionPropertyResolver implements ApiGatewayIntegrationExtensionPropertyResolver {

  private final GlobalOperationParameterApiGatewayIntegrationExtensionPropertyResolver globalRequestParametersResolver;
  private final MethodParametersApiGatewayExtensionPropertyResolver methodRequestParametersResolver;

  @Override
  public RequestParametersVendorExtension resolve(OperationContext context) {

    // Resolve global parameters
    final List<RequestParamVendorExtension> globalParameters = globalRequestParametersResolver.resolve(context.getGlobalOperationParameters());

    // Find method annotation
    final RequestParameter[] requestParameters = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::requestParameters)
        .orNull();

    // Resolve method parameters
    final List<RequestParamVendorExtension> methodParameters = methodRequestParametersResolver.resolve(context.getParameters(), requestParameters);

    final List<RequestParamVendorExtension> filteredGlobalParameters = globalParameters.stream()
        .filter(gParam -> methodParameters.stream().anyMatch(mParam -> mParam.getName().equals(gParam.getName())))
        .collect(Collectors.toList());
    methodParameters.addAll(filteredGlobalParameters);

    return new RequestParametersVendorExtension(methodParameters);
  }
}
