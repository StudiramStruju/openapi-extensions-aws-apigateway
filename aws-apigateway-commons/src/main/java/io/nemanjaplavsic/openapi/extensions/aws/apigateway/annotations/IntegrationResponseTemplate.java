package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationResponseTemplate {

  /**
   * An example of the MIME type is application/json.
   */
  String mediaType() default MediaType.ALL_VALUE;

  /**
   * For information about creating a mapping template, see
   * <a href="https://docs.aws.amazon.com/apigateway/latest/developerguide/rest-api-data-transformations.html#models-mappings-mappings">
   * and
   * <a href="https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-mapping-template-reference.html#input-variable-reference">
   * Mapping templates.</a>
   */
  String template() default "$input.body";
}
