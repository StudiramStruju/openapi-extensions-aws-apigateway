package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.ApiGatewayIntegrationExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.ApiGatewayIntegrationExtensionResolver;
import io.swagger.v3.oas.models.Operation;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.web.method.HandlerMethod;

public class ApiGatewayIntegrationOperationCustomizer implements OperationCustomizer {

  private final ApiGatewayIntegrationExtensionResolver apiGatewayIntegrationExtensionResolver;

  public ApiGatewayIntegrationOperationCustomizer(ApiGatewayIntegrationExtensionResolver apiGatewayIntegrationExtensionResolver) {
    this.apiGatewayIntegrationExtensionResolver = apiGatewayIntegrationExtensionResolver;
  }

  @Override
  public Operation customize(Operation operation, HandlerMethod handlerMethod) {
    final ApiGatewayIntegrationExtension extension = apiGatewayIntegrationExtensionResolver.resolve(operation, handlerMethod);
    operation.addExtension(extension.getExtensionKey(), extension.getExtensionValue());
    return operation;
  }
}
