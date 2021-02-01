package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum IntegrationResponseParameterType {
  HEADER("header"),
  BODY("body"),
  NONE(null);

  private final String key;

  IntegrationResponseParameterType(String key) {
    this.key = key;
  }

  public String key() {
    return this.key;
  }
}
