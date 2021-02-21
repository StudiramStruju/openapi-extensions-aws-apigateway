package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.HttpMethod;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration.IntegrationHttpMethodExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.util.HttpsUriInfo;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.util.OperationAnnotationUtils;
import io.swagger.v3.oas.models.Operation;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Optional;

@Component
public class IntegrationHttpMethodResolver implements IntegrationResolver<IntegrationHttpMethodExtension> {

  @Override
  public IntegrationHttpMethodExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    final HttpMethod httpMethod = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::httpMethod)
        .orElse(null);


    final HttpsUriInfo httpsUriInfo = OperationAnnotationUtils.getHttpsUriInfo(handlerMethod);

    return new IntegrationHttpMethodExtension(httpsUriInfo.getRequestMethod(), httpMethod);
  }
}
