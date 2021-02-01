package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponse;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseVendorExtension;
import io.swagger.annotations.ApiResponse;

import java.util.List;

public interface MethodResponseResolver {

  List<ResponseVendorExtension> resolve(List<ApiResponse> apiResponses, List<IntegrationResponse> respons);
}
