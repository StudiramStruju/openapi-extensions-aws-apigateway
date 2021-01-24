package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum IntegrationType implements EnumKey {
  /**
   * For integrating the API method request with an AWS service action, including the Lambda function-invoking action. With the Lambda function-invoking action, this is referred to as the Lambda custom integration. With any other AWS service action, this is known as AWS integration.
   */
  AWS("aws"),
  /**
   * For integrating the API method request with the Lambda function-invoking action with the client request passed through as-is. This integration is also referred to as the Lambda proxy integration.
   */
  AWS_PROXY("aws_proxy"),
  /**
   * For integrating the API method request with an HTTP endpoint, including a private HTTP endpoint within a VPC. This integration is also referred to as the HTTP custom integration.
   */
  HTTP("http"),
  /**
   * For integrating the API method request with an HTTP endpoint, including a private HTTP endpoint within a VPC, with the client request passed through as-is. This is also referred to as the HTTP proxy integration.
   */
  HTTP_PROXY("http_proxy"),
  /**
   * For integrating the API method request with API Gateway as a "loop-back" endpoint without invoking any backend.
   */
  MOCK("mock");

  private final String key;

  IntegrationType(final String key) {
    this.key = key;
  }

  public String key() {
    return key;
  }
}
