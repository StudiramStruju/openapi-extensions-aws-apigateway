package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterSource;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationResponseParameter {

  IntegrationResponseParameterSource source();

  String methodHeaderName();

  IntegrationResponseParameterType integrationParameterType() default IntegrationResponseParameterType.NONE;

  String staticValue() default "";

  String integrationParameterName() default "";
}
