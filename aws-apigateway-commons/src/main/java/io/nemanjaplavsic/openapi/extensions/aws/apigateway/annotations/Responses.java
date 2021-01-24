package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Responses {

  /**
   * Selection regular expression used to match the integration
   * response to the method response. For HTTP integrations,
   * this regex applies to the integration response status code.
   * For Lambda invocations, the regex applies to the errorMessage
   * field of the error information object returned by AWS Lambda
   * as a failure response body when the Lambda function execution
   * throws an exception.
   * <br><br>
   * Note<br>
   * The Response status pattern property name refers to a response
   * status code or regular expression describing a group of response
   * status codes. It does not correspond to any identifier of an
   * IntegrationResponse resource in the API Gateway REST API.
   */
  String responseStatusPattern() default "default";

  /**
   * Defines a response and specifies parameter mappings or payload
   * mappings from the integration response to the method response.
   */
  Response response();
}
