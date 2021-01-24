package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParamType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.MethodRequestParamType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.RequestParamSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParameter {

  /**
   * Source from where to map parameter to integration request.
   */
  RequestParamSource source();

  /**
   * integration.request.<b>{type}</b>.{name} mapping <b>type</b>
   */
  IntegrationRequestParamType integrationRequestParamType() default IntegrationRequestParamType.NONE;


  /**
   * integration.request{type}.<b>{name}</b> mapping <b>name</b>
   */
  String integrationRequestParamName() default "";

  /**
   * depending on the type can be:
   * <ul>
   *     <li>path
   *     <li>querystring</li>
   *     <li>header</li>
   * </ul>
   * <p>
   *  method.response.<b>{type}</b>.{name}</li>
   */
  MethodRequestParamType methodRequestParamType() default MethodRequestParamType.NONE;


  String methodRequestParamName() default "";

  /**
   * context will map to  <b>context</b>.{name}
   */
  String contextVariableName() default "";

  /**
   * stageVariables will map to stageVariables.<b>{name}</b>
   */
  String stageVariableName() default "";

  /**
   * STATIC_VALUE will map to just {name}
   */
  String staticValueName() default "";
}
