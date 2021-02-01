package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum ContentHandling {
  /**
   * When no use for this feature
   */
  NONE(null),
  /**
   * For converting a binary payload into a base64-encoded string or converting a text payload into a utf-8-encoded string or passing through the text payload natively without modification
   */
  CONVERT_TO_TEXT("CONVERT_TO_TEXT"),
  /**
   * For converting a text payload into a base64-decoded blob or passing through a binary payload natively without modification
   */
  CONVERT_TO_BINARY("CONVERT_TO_BINARY");

  private final String key;

  ContentHandling(final String key) {
    this.key = key;
  }

  public String key() {
    return key;
  }
}
