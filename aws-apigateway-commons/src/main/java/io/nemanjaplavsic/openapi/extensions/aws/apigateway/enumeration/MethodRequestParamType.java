package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum MethodRequestParamType implements EnumKey {
  PATH("path"),
  QUERY("querystring"),
  HEADER("header"),
  /**
   * If not used
   */
  NONE(null);

  private final String key;

  MethodRequestParamType(String key) {
    this.key = key;
  }

  public static MethodRequestParamType matching(IntegrationRequestParamType type) {
    switch (type) {
      case QUERY:
        return MethodRequestParamType.QUERY;
      case PATH:
        return MethodRequestParamType.PATH;
      case HEADER:
        return MethodRequestParamType.HEADER;
      default:
        return MethodRequestParamType.NONE;
    }
  }

  public String key() {
    return this.key;
  }
}
