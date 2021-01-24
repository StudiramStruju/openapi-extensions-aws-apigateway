package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum PassThroughBehavior implements EnumKey {
  /**
   * Passes the method request body through the integration request to the back end without transformation when the method request content type does not match any content type associated with the mapping templates defined in the integration request.
   */
  WHEN_NO_MATCH("when_no_match"),
  /**
   * Passes the method request body through the integration request to the back end without transformation when no mapping template is defined in the integration request. If a template is defined when this option is selected, the method request of an unmapped content-type will be rejected with an HTTP 415 Unsupported Media Type response.
   */
  WHEN_NO_TEMPLATES("when_no_templates"),
  /**
   * Rejects the method request with an HTTP 415 Unsupported Media Type response when either the method request content type does not match any content type associated with the mapping templates defined in the integration request or no mapping template is defined in the integration request.
   */
  NEVER("never"),

  /**
   * When not used
   */
  NONE(null);

  private final String key;

  PassThroughBehavior(final String key) {
    this.key = key;
  }

  public String key() {
    return key;
  }
}
