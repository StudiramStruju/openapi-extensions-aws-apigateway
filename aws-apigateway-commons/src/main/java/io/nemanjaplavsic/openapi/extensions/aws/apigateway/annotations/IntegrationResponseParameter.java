package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ResponseParameterSource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationResponseParameter {

  ResponseParameterSource source();

  String methodHeaderName();

  IntegrationResponseParameterType integrationParameterType() default IntegrationResponseParameterType.NONE;

  String staticValue() default "";

  String integrationParameterName() default  "";
}
