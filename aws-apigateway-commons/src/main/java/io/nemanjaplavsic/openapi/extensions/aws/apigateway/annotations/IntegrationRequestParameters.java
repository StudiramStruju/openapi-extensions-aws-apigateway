package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * For REST APIs, the value is typically a predefined method request parameter of the
 * method.request.<param-type>.<param-name> format, where <param-type> can be:
 * <ul>
 *     <li>querystring</li>
 *     <li>path</li>
 *     <li>header</li>
 *     <li>body</li>
 * </ul>
 * However,
 * <ul>
 *     <li>$context.VARIABLE_NAME</li>
 *     <li>$stageVariables.VARIABLE_NAME</li>
 *     <li>STATIC_VALUE</li>
 * </ul>
 * are also valid. For the body parameter, the <param-name> is a JSON path expression without the $. prefix.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationRequestParameters {
  IntegrationRequestParameter[] value() default {};
}
