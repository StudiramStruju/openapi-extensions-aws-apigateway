package io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ConnectionType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.HttpMethod;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.PassThroughBehavior;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies details of the backend integration used for this method. This extension is an extended property
 * of the OpenAPI Operation object. The result is an API Gateway integration object.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiGatewayIntegration {

  /**
   * A list of request parameters whose values are to be cached.
   */
  String[] cacheKeyParameters() default {};

  /**
   * An API-specific tag group of related cached parameters.
   */
  String cacheNamespace() default "";

  /**
   * The ID of a VpcLink for the private integration.<br>
   * Can be set in properties by setting <b>api-gateway-integration.defaults.connection-id</b>
   */
  String connectionId() default "";

  /**
   * The integration connection type. The valid value is "VPC_LINK" for private integration or
   * "INTERNET", otherwise.<br>
   * Can be set in properties by setting <b>api-gateway-integration.defaults.connection-type</b>
   */
  ConnectionType connectionType() default ConnectionType.NONE;

  /**
   * For AWS IAM role-based credentials, specify the ARN of an appropriate IAM role.
   * If unspecified, credentials default to resource-based permissions that must be added manually
   * to allow the API to access the resource. For more information,
   * see <a href="https://docs.aws.amazon.com/lambda/latest/dg/intro-permission-model.html#intro-permission-model-access-policy">
   * Granting Permissions Using a Resource Policy</a>.
   * <br><br>
   * Note: When using IAM credentials, make sure that
   * <a href="https://docs.aws.amazon.com/IAM/latest/UserGuide/id_credentials_temp_enable-regions.html">AWS STS Regional endpoints</a>
   * are enabled for the Region where this API is deployed for best performance.
   */
  String credentials() default "";

  /**
   * Request payload encoding conversion types. Valid values are: <br>
   * 1) CONVERT_TO_TEXT, for converting a binary payload into a base64-encoded
   * string or converting a text payload into a utf-8-encoded string or passing
   * through the text payload natively without modification, and <br>
   * 2) CONVERT_TO_BINARY, for converting a text payload into a base64-decoded
   * blob or passing through a binary payload natively without modification.<br>
   */
  ContentHandling contentHandling() default ContentHandling.NONE;

  /**
   * The HTTP method used in the integration request. For Lambda function invocations, t
   * he value must be POST.<br>
   *
   * @see HttpMethod
   */
  HttpMethod httpMethod() default HttpMethod.RESOLVE_FROM_METHOD;

  /**
   * Specifies how a request payload of unmapped content type is passed
   * through the integration request without modification. Supported values are when_no_templates,
   * when_no_match, and never. For more information, see
   * <a href="https://docs.aws.amazon.com/apigateway/api-reference/resource/integration/#passthroughBehavior">
   * Integration.passthroughBehavior</a>.
   */
  PassThroughBehavior passthroughBehavior() default PassThroughBehavior.NONE;

  /**
   * Integration timeouts between 50 ms and 29,000 ms.
   */
  int timeoutInMillis() default -1;

  /**
   * The type of integration with the specified backend
   * <ul>
   *     <li>http or http_proxy, for integration with an HTTP backend.</li>
   *     <li>aws_proxy, for integration with AWS Lambda functions</li>
   *     <li>aws, for integration with AWS Lambda functions or other AWS services,
   *     such as Amazon DynamoDB, Amazon Simple Notification Service, or Amazon Simple Queue Service</li>
   *     <li>mock, for integration with API Gateway without invoking any backend</li>
   * </ul>
   * For more information about the integration types, see <a href="https://docs.aws.amazon.com/apigateway/api-reference/resource/integration/#type">integration:type</a><br>
   * Can be set in properties
   */
  IntegrationType type() default IntegrationType.AWS;

  /**
   * The endpoint URI of the backend. For integrations of the aws type, this is an ARN value.
   * For the HTTP integration, this is the URL of the HTTP endpoint including the https or http scheme.
   */
  String uri() default "";

}
