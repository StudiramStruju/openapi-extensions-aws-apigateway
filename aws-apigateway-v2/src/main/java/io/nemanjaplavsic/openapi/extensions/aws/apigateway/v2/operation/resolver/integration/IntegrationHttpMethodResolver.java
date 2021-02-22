package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.HttpMethod;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationHttpMethodExtension;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.spi.service.contexts.OperationContext;

@Component
public class IntegrationHttpMethodResolver implements IntegrationResolver<IntegrationHttpMethodExtension> {

  @Override
  public IntegrationHttpMethodExtension resolve(OperationContext context) {
    final HttpMethod httpMethod = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::httpMethod)
        .orNull();

    final RequestMethod requestMethod = RequestMethod.valueOf(context.httpMethod().name());

    return new IntegrationHttpMethodExtension(requestMethod, httpMethod);
  }
}
