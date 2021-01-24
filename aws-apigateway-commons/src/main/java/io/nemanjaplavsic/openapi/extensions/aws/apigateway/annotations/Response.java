package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents x-amazon-apigateway-integration.response extension
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Response {

  /**
   * HTTP status code for the method response; for example, 200. This must correspond to a matching response in the OpenAPI Operation responses field.
   */
  int statusCode() default 200;

  /**
   * Specifies MIME type-specific mapping templates for the response’s payload.<br>
   * Not required. If not defined it will default to "application/json" : "$input.json('$')"
   */
  ResponseTemplate[] templates() default {@ResponseTemplate};

  /**
   * Specifies mappings from integration method response parameters to method response parameters. Only the header and body types of the integration response parameters can be mapped to the header type of the method response.
   */
  ResponseParameters[] responseParameters() default {};

  /**
   * Response payload encoding conversion types. Valid values are 1) CONVERT_TO_TEXT, for converting a binary payload into a base64-encoded string or converting a text payload into a utf-8-encoded string or passing through the text payload natively without modification, and 2) CONVERT_TO_BINARY, for converting a text payload into a base64-decoded blob or passing through a binary payload natively without modification.
   * Set to NONE as default which means that this property will not be included in OpenApi
   */
  ContentHandling contentHandling() default ContentHandling.NONE;

}
