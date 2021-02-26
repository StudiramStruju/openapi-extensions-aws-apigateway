package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver;


import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.OperationApiGatewayExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.web.method.HandlerMethod;

public interface OperationAwsExtensionResolver<E extends OperationApiGatewayExtension<?>> {

  E resolve(Operation operation, HandlerMethod handlerMethod);
}
