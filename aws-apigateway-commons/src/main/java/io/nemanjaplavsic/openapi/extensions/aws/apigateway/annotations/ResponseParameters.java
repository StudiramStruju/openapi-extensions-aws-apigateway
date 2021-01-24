package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationResponseParameterType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseParameters {

  String methodHeaderName();

  IntegrationResponseParameterType integrationResponseParamType();

  String integrationResponseParamName();


}
