package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum ConnectionType implements EnumKey {
  /**
   * For Private integration
   */
  VPC_LINK("VPC_LINK"),
  /**
   * For Public integration
   */
  INTERNET("INTERNET"),
  /**
   * When not used
   */
  NONE(null);

  private final String key;

  ConnectionType(final String key) {
    this.key = key;
  }

  public String key() {
    return key;
  }
}
