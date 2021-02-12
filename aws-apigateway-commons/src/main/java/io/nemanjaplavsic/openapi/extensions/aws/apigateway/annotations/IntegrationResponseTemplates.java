package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationResponseTemplates {
  IntegrationResponseTemplate[] value() default {@IntegrationResponseTemplate};
}
