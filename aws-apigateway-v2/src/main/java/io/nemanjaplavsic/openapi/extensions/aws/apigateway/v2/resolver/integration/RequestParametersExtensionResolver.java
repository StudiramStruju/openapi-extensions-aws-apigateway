package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationRequestParameter;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.request.GlobalOperationParametersResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.request.MethodParametersResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.RequestParametersVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.RequestParamVendorExtension;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestParametersExtensionResolver implements ApiGatewayPropertyExtensionResolver {

  private final GlobalOperationParametersResolver globalRequestParametersResolver;
  private final MethodParametersResolver methodRequestParametersResolver;

  @Override
  public RequestParametersVendorExtension resolve(OperationContext context) {

    // Resolve global parameters
    final List<RequestParamVendorExtension> globalParameters = globalRequestParametersResolver.resolve(context.getGlobalOperationParameters());

    // Find method annotation
    final IntegrationRequestParameter[] integrationRequestParameters = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::requestParameters)
        .orNull();

    // Resolve method parameters
    final List<RequestParamVendorExtension> methodParameters = methodRequestParametersResolver.resolve(context.getParameters(), integrationRequestParameters);

    return new RequestParametersVendorExtension(merge(globalParameters, methodParameters));
  }

  public List<RequestParamVendorExtension> merge(List<RequestParamVendorExtension> globalParameters,
                                                 List<RequestParamVendorExtension> methodParameters) {
    final List<RequestParamVendorExtension> filteredGlobalParameters = globalParameters.stream()
        .filter(gParam -> methodParameters.stream().anyMatch(mParam -> mParam.getName().equals(gParam.getName())))
        .collect(Collectors.toList());

    final List<RequestParamVendorExtension> result = new ArrayList<>(methodParameters);
    result.addAll(filteredGlobalParameters);
    return result;
  }
}
