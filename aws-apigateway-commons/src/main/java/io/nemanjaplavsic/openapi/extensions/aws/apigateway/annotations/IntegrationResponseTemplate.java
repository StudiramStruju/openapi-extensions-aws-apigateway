package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationResponseTemplate {

  /**
   * An example of the MIME type is application/json.
   */
  String mediaType() default MediaType.APPLICATION_JSON_VALUE;

  /**
   * Mapping template. If not defined it will default to $input.json('$') which passes json trough from backend service.
   *
   * @return response template
   */
  String template() default "$input.json('$')";
}
