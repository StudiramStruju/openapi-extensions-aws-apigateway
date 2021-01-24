package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.exception;

public class NoAnnotationException extends ApiGatewayIntegrationExtensionException {
  public NoAnnotationException(String message) {
    super(message);
  }

  public NoAnnotationException(Throwable cause) {
    super(cause);
  }

  public NoAnnotationException(String message, Throwable cause) {
    super(message, cause);
  }
}
