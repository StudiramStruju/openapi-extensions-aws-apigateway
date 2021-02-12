package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mapping templates for a request payload of specified MIME types.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationRequestTemplates {
  IntegrationRequestTemplate[] value() default {};
}
