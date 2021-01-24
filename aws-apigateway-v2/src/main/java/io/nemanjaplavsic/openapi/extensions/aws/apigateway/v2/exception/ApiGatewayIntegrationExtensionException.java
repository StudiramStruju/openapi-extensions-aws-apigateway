package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.exception;

public abstract class ApiGatewayIntegrationExtensionException extends Exception {

  protected ApiGatewayIntegrationExtensionException(String message) {
    super(message);
  }

  protected ApiGatewayIntegrationExtensionException(Throwable cause) {
    super(cause);
  }

  protected ApiGatewayIntegrationExtensionException(String message, Throwable cause) {
    super(message, cause);
  }

}
