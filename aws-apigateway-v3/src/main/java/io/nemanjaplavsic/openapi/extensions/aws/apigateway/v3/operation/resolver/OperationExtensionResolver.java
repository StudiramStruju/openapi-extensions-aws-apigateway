package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver;


import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.ApiGatewayOperationExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.web.method.HandlerMethod;

public interface OperationExtensionResolver<E extends ApiGatewayOperationExtension<?>> {

  E resolve(Operation operation, HandlerMethod handlerMethod);
}
