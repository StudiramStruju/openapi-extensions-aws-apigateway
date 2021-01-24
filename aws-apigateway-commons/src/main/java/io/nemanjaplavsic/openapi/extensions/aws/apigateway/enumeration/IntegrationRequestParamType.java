package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum IntegrationRequestParamType implements EnumKey {
  PATH("path"),
  QUERY("querystring"),
  HEADER("header"),
  /**
   * If not used
   */
  NONE(null);

  private final String key;

  IntegrationRequestParamType(final String key) {
    this.key = key;
  }

  public static IntegrationRequestParamType matching(MethodRequestParamType type) {
    switch (type) {
      case QUERY:
        return IntegrationRequestParamType.QUERY;
      case PATH:
        return IntegrationRequestParamType.PATH;
      case HEADER:
        return IntegrationRequestParamType.HEADER;
      default:
        return IntegrationRequestParamType.NONE;
    }
  }

  public String key() {
    return this.key;
  }
}
