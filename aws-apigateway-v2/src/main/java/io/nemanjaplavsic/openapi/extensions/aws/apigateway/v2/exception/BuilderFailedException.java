package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.exception;

public class BuilderFailedException extends ApiGatewayIntegrationExtensionException {

  public BuilderFailedException(String message) {
    super(message);
  }

  public BuilderFailedException(Throwable cause) {
    super(cause);
  }

  public BuilderFailedException(String message, Throwable cause) {
    super(message, cause);
  }
}
