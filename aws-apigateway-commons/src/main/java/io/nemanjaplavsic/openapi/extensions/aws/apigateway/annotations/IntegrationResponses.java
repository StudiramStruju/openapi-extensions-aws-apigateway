package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the method's responses and specifies desired parameter mappings or
 * payload mappings from integration responses to method responses.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationResponses {
  /**
   * Represents x-amazon-apigateway-integration.response extension
   */
  IntegrationResponse[] value() default {};
}
