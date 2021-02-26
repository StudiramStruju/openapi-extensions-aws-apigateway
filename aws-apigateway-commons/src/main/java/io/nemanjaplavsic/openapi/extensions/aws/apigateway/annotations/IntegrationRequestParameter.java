package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.MethodRequestParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.RequestParameterSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationRequestParameter {

  /**
   * Source from where to map parameter to integration request.
   */
  RequestParameterSource source();

  /**
   * integration.request.<b>{type}</b>.{name} mapping <b>type</b>
   */
  IntegrationRequestParameterType integrationParameterType();


  /**
   * integration.request{type}.<b>{name}</b> mapping <b>name</b>
   */
  String integrationParameterName();

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
  MethodRequestParameterType methodParameterType() default MethodRequestParameterType.NONE;


  String methodParameterName() default "";

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
