package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.RequestParameter;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.RequestParamVendorExtension;
import org.springframework.lang.Nullable;
import springfox.documentation.service.ResolvedMethodParameter;

import java.util.List;

public interface MethodParametersApiGatewayExtensionPropertyResolver {

  List<RequestParamVendorExtension> resolve(List<ResolvedMethodParameter> methodParameters, @Nullable RequestParameter[] annotationParameters);
}
