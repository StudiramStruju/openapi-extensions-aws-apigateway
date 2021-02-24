package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.gatewayresponse.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.gatewayresponse.ResponseExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationContentHandlingExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationStatusCodeExtension;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class GatewayResponseExtension implements ResponseExtension<LinkedHashMap<String, Object>> {

  private String responseStatusPattern;
  private IntegrationStatusCodeExtension statusCode;
  private IntegrationContentHandlingExtension contentHandling;
  private final GatewayResponseTemplatesExtension templates;
  private final GatewayResponseParametersExtension parameters;

  public GatewayResponseExtension(String responseStatusPattern,
                                  int statusCode) {
    this(responseStatusPattern, new IntegrationStatusCodeExtension(statusCode), null, null, null);
  }

  public GatewayResponseExtension(String responseStatusPattern,
                                  IntegrationStatusCodeExtension statusCode) {
    this(responseStatusPattern, statusCode, null, null, null);
  }

  public GatewayResponseExtension(String responseStatusPattern,
                                  IntegrationStatusCodeExtension statusCode,
                                  @Nullable IntegrationContentHandlingExtension contentHandling,
                                  @Nullable GatewayResponseTemplatesExtension templates,
                                  @Nullable GatewayResponseParametersExtension parameters) {
    this.responseStatusPattern = responseStatusPattern;
    this.statusCode = statusCode;
    this.contentHandling = Objects.requireNonNullElse(contentHandling, new IntegrationContentHandlingExtension());
    this.templates = Objects.requireNonNullElse(templates, new GatewayResponseTemplatesExtension());
    this.parameters = Objects.requireNonNullElse(parameters, new GatewayResponseParametersExtension());
  }

  /**
   * The gateway response for authorization failure—for example, when access is denied by a custom or Amazon Cognito authorizer. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension ACCESS_DENIED() {
    return new GatewayResponseExtension("ACCESS_DENIED", 403);
  }

  /**
   * The gateway response for an invalid API configuration—including when an invalid endpoint address is submitted, when base64 decoding fails on binary data when binary support is enacted, or when integration response mapping can't match any template and no default template is configured. If the response type is unspecified, this response defaults to the DEFAULT_5XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension API_CONFIGURATION_ERROR() {
    return new GatewayResponseExtension("API_CONFIGURATION_ERROR", 500);
  }

  /**
   * The gateway response when a custom or Amazon Cognito authorizer failed to authenticate the caller. If the response type is unspecified, this response defaults to the DEFAULT_5XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension AUTHORIZER_CONFIGURATION_ERROR() {
    return new GatewayResponseExtension("AUTHORIZER_CONFIGURATION_ERROR", 500);
  }

  /**
   * The gateway response when the request parameter cannot be validated according to an enabled request validator. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension BAD_REQUEST_PARAMETERS() {
    return new GatewayResponseExtension("BAD_REQUEST_PARAMETERS", 400);
  }

  /**
   * The gateway response when the request body cannot be validated according to an enabled request validator. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension BAD_REQUEST_BODY() {
    return new GatewayResponseExtension("BAD_REQUEST_BODY", 400);
  }

  /**
   * The default gateway response for an unspecified response type with the status code of 4XX. Changing the status code of this fallback gateway response changes the status codes of all other 4XX responses to the new value. Resetting this status code to null reverts the status codes of all other 4XX responses to their original values.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension DEFAULT_4XX(int defaultStatusCode) {
    return new GatewayResponseExtension("DEFAULT_4XX", defaultStatusCode);
  }

  /**
   * The default gateway response for an unspecified response type with a status code of 5XX. Changing the status code of this fallback gateway response changes the status codes of all other 5XX responses to the new value. Resetting this status code to null reverts the status codes of all other 5XX responses to their original values.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension DEFAULT_5XX(int defaultStatusCode) {
    return new GatewayResponseExtension("DEFAULT_5XX", defaultStatusCode);
  }

  /**
   * The gateway response for an AWS authentication token expired error. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension EXPIRED_TOKEN() {
    return new GatewayResponseExtension("EXPIRED_TOKEN", 403);
  }

  /**
   * The gateway response for an integration failed error. If the response type is unspecified, this response defaults to the DEFAULT_5XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension INTEGRATION_FAILURE() {
    return new GatewayResponseExtension("INTEGRATION_FAILURE", 504);
  }

  /**
   * The gateway response for an integration timed out error. If the response type is unspecified, this response defaults to the DEFAULT_5XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension INTEGRATION_TIMEOUT() {
    return new GatewayResponseExtension("INTEGRATION_TIMEOUT", 504);
  }

  /**
   * The gateway response for an invalid API key submitted for a method requiring an API key. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension INVALID_API_KEY() {
    return new GatewayResponseExtension("INVALID_API_KEY", 403);
  }

  /**
   * The gateway response for an invalid AWS signature error. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension INVALID_SIGNATURE() {
    return new GatewayResponseExtension("INVALID_SIGNATURE", 403);
  }

  /**
   * The gateway response for a missing authentication token error, including the cases when the client attempts to invoke an unsupported API method or resource. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension MISSING_AUTHENTICATION_TOKEN() {
    return new GatewayResponseExtension("MISSING_AUTHENTICATION_TOKEN", 403);
  }

  /**
   * The gateway response for the usage plan quota exceeded error. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension QUOTA_EXCEEDED() {
    return new GatewayResponseExtension("QUOTA_EXCEEDED", 429);
  }

  /**
   * The gateway response for the request too large error. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension REQUEST_TOO_LARGE() {
    return new GatewayResponseExtension("REQUEST_TOO_LARGE", 413);
  }

  /**
   * The gateway response when API Gateway cannot find the specified resource after an API request passes authentication and authorization, except for API key authentication and authorization. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension RESOURCE_NOT_FOUND() {
    return new GatewayResponseExtension("RESOURCE_NOT_FOUND", 404);
  }

  /**
   * The gateway response when usage plan-, method-, stage-, or account-level throttling limits exceeded. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension THROTTLED() {
    return new GatewayResponseExtension("THROTTLED", 429);
  }

  /**
   * The gateway response when the custom or Amazon Cognito authorizer failed to authenticate the caller.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension UNAUTHORIZED() {
    return new GatewayResponseExtension("UNAUTHORIZED", 401);
  }

  /**
   * The gateway response when a payload is of an unsupported media type, if strict passthrough behavior is enabled. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension UNSUPPORTED_MEDIA_TYPE() {
    return new GatewayResponseExtension("UNSUPPORTED_MEDIA_TYPE", 415);
  }

  /**
   * The gateway response when a request is blocked by AWS WAF. If the response type is unspecified, this response defaults to the DEFAULT_4XX type.
   * @return GatewayResponseExtension
   */
  public static GatewayResponseExtension WAF_FILTERED() {
    return new GatewayResponseExtension("WAF_FILTERED", 403);
  }


  public GatewayResponseExtension responseStatusPattern(String responseStatusPattern) {
    this.responseStatusPattern = responseStatusPattern;
    return this;
  }

  public GatewayResponseExtension statusCode(Integer statusCode) {
    this.statusCode = new IntegrationStatusCodeExtension(statusCode);
    return this;
  }

  public GatewayResponseExtension statusCode(IntegrationStatusCodeExtension statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public GatewayResponseExtension contentHandling(IntegrationContentHandlingExtension contentHandling) {
    this.contentHandling = contentHandling;
    return this;
  }

  public GatewayResponseExtension contentHandling(ContentHandling contentHandling) {
    this.contentHandling = new IntegrationContentHandlingExtension(contentHandling);
    return this;
  }

  public GatewayResponseExtension templates(GatewayResponseTemplatesExtension templates) {
    this.templates.templates(templates.templates());
    return this;
  }

  public GatewayResponseExtension template(GatewayResponseTemplateExtension template) {
    this.templates.template(template);
    return this;
  }

  public GatewayResponseExtension parameters(GatewayResponseParametersExtension parameters) {
    this.parameters.parameters(parameters.parameters());
    return this;
  }

  public GatewayResponseExtension parameter(GatewayResponseParameterExtension parameter) {
    this.parameters.parameter(parameter);
    return this;
  }

  public String responseStatusPattern() {
    return responseStatusPattern;
  }

  public IntegrationStatusCodeExtension statusCode() {
    return statusCode;
  }

  public IntegrationContentHandlingExtension contentHandling() {
    return contentHandling;
  }

  public GatewayResponseTemplatesExtension templates() {
    return templates;
  }

  public GatewayResponseParametersExtension parameters() {
    return parameters;
  }

  public boolean matches(GatewayResponseExtension response) {
    return Pattern.matches(responseStatusPattern, response.responseStatusPattern());
  }

  public GatewayResponseExtension update(GatewayResponseExtension response) {
    return this.statusCode(response.statusCode())
        .contentHandling(response.contentHandling())
        .templates(response.templates())
        .parameters(response.parameters());
  }

  @Override
  public String getExtensionKey() {
    return responseStatusPattern;
  }

  @Override
  public LinkedHashMap<String, Object> getExtensionValue() {
    LinkedHashMap<String, Object> extension = new LinkedHashMap<>();
    extension.put(statusCode.getExtensionKey(), statusCode.getExtensionValue());
    if (templates.isValid()) extension.put(templates.getExtensionKey(), templates.getExtensionValue());
    if (parameters.isValid()) extension.put(parameters.getExtensionKey(), parameters.getExtensionValue());
    if (contentHandling.isValid()) extension.put(contentHandling.getExtensionKey(), contentHandling.getExtensionValue());
    return extension;
  }


  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof GatewayResponseExtension)) return false;
    GatewayResponseExtension that = (GatewayResponseExtension) object;
    return responseStatusPattern.equals(that.responseStatusPattern) &&
        statusCode.equals(that.statusCode) &&
        contentHandling.equals(that.contentHandling) &&
        templates.equals(that.templates) &&
        parameters.equals(that.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseStatusPattern, statusCode, contentHandling, templates, parameters);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GatewayResponseExtension.class.getSimpleName() + "[", "]")
        .add("responseStatusPattern='" + responseStatusPattern + "'")
        .add("statusCode=" + statusCode)
        .add("contentHandling=" + contentHandling)
        .add("templates=" + templates)
        .add("parameters=" + parameters)
        .toString();
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(responseStatusPattern);
      if (!StringUtils.hasText(responseStatusPattern)) return false;
      Objects.requireNonNull(statusCode);
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }
}
