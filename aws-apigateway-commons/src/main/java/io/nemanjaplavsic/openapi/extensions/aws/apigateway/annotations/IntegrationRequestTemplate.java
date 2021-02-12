package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationRequestTemplate {

  /**
   * An example of the MIME type is application/json.
   */
  String mediaType() default MediaType.APPLICATION_JSON_VALUE;

  /**
   * For information about creating a mapping template, see
   * <a href="https://docs.aws.amazon.com/apigateway/latest/developerguide/rest-api-data-transformations.html#models-mappings-mappings">
   * Mapping templates.</a>
   */
  String template() default "$input.json('$')";

  // TODO: 10/10/2020 Add some way to reference templates from external sources
}