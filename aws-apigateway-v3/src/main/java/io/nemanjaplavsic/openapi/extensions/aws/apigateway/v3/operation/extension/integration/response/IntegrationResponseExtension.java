package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ConvertableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ValidatableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationContentHandlingExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationStatusCodeExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.parameter.IntegrationResponseParameterExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.parameter.IntegrationResponseParametersExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.template.IntegrationResponseTemplateExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.template.IntegrationResponseTemplatesExtension;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class IntegrationResponseExtension implements ConvertableExtension<LinkedHashMap<String, Object>>, ValidatableExtension {

  private final IntegrationResponseTemplatesExtension templates;
  private final IntegrationResponseParametersExtension parameters;
  private String responseStatusPattern;
  private IntegrationStatusCodeExtension statusCode;
  private IntegrationContentHandlingExtension contentHandling;

  public IntegrationResponseExtension(String responseStatusPattern,
                                      int statusCode) {
    this(responseStatusPattern, new IntegrationStatusCodeExtension(statusCode), null, null, null);
  }

  public IntegrationResponseExtension(String responseStatusPattern,
                                      IntegrationStatusCodeExtension statusCode) {
    this(responseStatusPattern, statusCode, null, null, null);
  }

  public IntegrationResponseExtension(String responseStatusPattern,
                                      IntegrationStatusCodeExtension statusCode,
                                      @Nullable IntegrationContentHandlingExtension contentHandling,
                                      @Nullable IntegrationResponseTemplatesExtension templates,
                                      @Nullable IntegrationResponseParametersExtension parameters) {
    this.responseStatusPattern = responseStatusPattern;
    this.statusCode = statusCode;
    this.contentHandling = Objects.requireNonNullElse(contentHandling, new IntegrationContentHandlingExtension());
    this.templates = Objects.requireNonNullElse(templates, new IntegrationResponseTemplatesExtension());
    this.parameters = Objects.requireNonNullElse(parameters, new IntegrationResponseParametersExtension());
  }

  public IntegrationResponseExtension responseStatusPattern(String responseStatusPattern) {
    this.responseStatusPattern = responseStatusPattern;
    return this;
  }

  public IntegrationResponseExtension statusCode(Integer statusCode) {
    this.statusCode = new IntegrationStatusCodeExtension(statusCode);
    return this;
  }

  public IntegrationResponseExtension statusCode(IntegrationStatusCodeExtension statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public IntegrationResponseExtension contentHandling(IntegrationContentHandlingExtension contentHandling) {
    this.contentHandling = contentHandling;
    return this;
  }

  public IntegrationResponseExtension contentHandling(ContentHandling contentHandling) {
    this.contentHandling = new IntegrationContentHandlingExtension(contentHandling);
    return this;
  }

  public IntegrationResponseExtension templates(IntegrationResponseTemplatesExtension templates) {
    this.templates.templates(templates.getTemplates());
    return this;
  }

  public IntegrationResponseExtension template(IntegrationResponseTemplateExtension template) {
    this.templates.template(template);
    return this;
  }

  public IntegrationResponseExtension parameters(IntegrationResponseParametersExtension parameters) {
    this.parameters.parameters(parameters.getParameters());
    return this;
  }

  public IntegrationResponseExtension parameter(IntegrationResponseParameterExtension parameter) {
    this.parameters.parameter(parameter);
    return this;
  }

  public String getResponseStatusPattern() {
    return responseStatusPattern;
  }

  public IntegrationStatusCodeExtension getStatusCode() {
    return statusCode;
  }

  public IntegrationContentHandlingExtension getContentHandling() {
    return contentHandling;
  }

  public IntegrationResponseTemplatesExtension getTemplates() {
    return templates;
  }

  public IntegrationResponseParametersExtension getParameters() {
    return parameters;
  }

  public boolean matches(IntegrationResponseExtension response) {
    return Pattern.matches(responseStatusPattern, response.getResponseStatusPattern());
  }

  public IntegrationResponseExtension update(IntegrationResponseExtension response) {
    return this.statusCode(response.getStatusCode())
        .contentHandling(response.getContentHandling())
        .templates(response.getTemplates())
        .parameters(response.getParameters());
  }

  public String getExtensionKey() {
    return getResponseStatusPattern();
  }

  public LinkedHashMap<String, Object> getExtensionValue() {
    LinkedHashMap<String, Object> extension = new LinkedHashMap<>();
    extension.put(statusCode.getExtensionKey(), statusCode.getExtensionValue());
    if (templates.isValid()) extension.put(templates.getExtensionKey(), templates.getExtensionValue());
    if (parameters.isValid()) extension.put(parameters.getExtensionKey(), parameters.getExtensionValue());
    if (contentHandling.isValid()) extension.put(contentHandling.getExtensionKey(), contentHandling.getExtensionValue());
    return extension;
  }

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

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationResponseExtension)) return false;
    IntegrationResponseExtension that = (IntegrationResponseExtension) object;
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
    return new StringJoiner(", ", IntegrationResponseExtension.class.getSimpleName() + "[", "]")
        .add("responseStatusPattern='" + responseStatusPattern + "'")
        .add("statusCode=" + statusCode)
        .add("contentHandling=" + contentHandling)
        .add("templates=" + templates)
        .add("parameters=" + parameters)
        .toString();
  }
}
