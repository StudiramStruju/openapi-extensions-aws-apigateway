package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.request.RequestParamVendorExtension;
import springfox.documentation.service.Parameter;

import java.util.List;

public interface GlobalOperationParametersResolver {

  List<RequestParamVendorExtension> resolve(List<Parameter> globalParameters);
}
