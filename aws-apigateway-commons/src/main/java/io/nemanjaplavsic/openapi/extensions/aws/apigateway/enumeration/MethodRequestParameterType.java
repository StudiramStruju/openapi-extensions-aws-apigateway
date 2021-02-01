package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum MethodRequestParameterType {
  PATH("path"),
  QUERY("querystring"),
  HEADER("header"),
  /**
   * If not used
   */
  NONE(null);

  private final String key;

  MethodRequestParameterType(String key) {
    this.key = key;
  }

  public static MethodRequestParameterType matching(IntegrationRequestParameterType type) {
    switch (type) {
      case QUERY:
        return MethodRequestParameterType.QUERY;
      case PATH:
        return MethodRequestParameterType.PATH;
      case HEADER:
        return MethodRequestParameterType.HEADER;
      default:
        return MethodRequestParameterType.NONE;
    }
  }

  public String key() {
    return this.key;
  }
}
