package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseVendorExtension;
import springfox.documentation.service.ResponseMessage;

import java.util.List;

public interface GlobalResponseResolver {

  List<ResponseVendorExtension> resolve(List<ResponseMessage> globalResponseMessages);
}
