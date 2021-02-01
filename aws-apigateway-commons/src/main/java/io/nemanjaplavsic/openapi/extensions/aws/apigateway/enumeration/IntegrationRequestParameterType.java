package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum IntegrationRequestParameterType {
  PATH("path"),
  QUERY("querystring"),
  HEADER("header");

  private final String key;

  IntegrationRequestParameterType(final String key) {
    this.key = key;
  }

  public static IntegrationRequestParameterType matching(MethodRequestParameterType type) {
    switch (type) {
      case QUERY:
        return IntegrationRequestParameterType.QUERY;
      case PATH:
        return IntegrationRequestParameterType.PATH;
      case HEADER:
        return IntegrationRequestParameterType.HEADER;
      default:
        throw new IllegalArgumentException(
            String.format("%s MethodRequestParameterType does not have macthing IntegrationRequestParameterType", type)
        );
    }
  }

  public String key() {
    return this.key;
  }
}
