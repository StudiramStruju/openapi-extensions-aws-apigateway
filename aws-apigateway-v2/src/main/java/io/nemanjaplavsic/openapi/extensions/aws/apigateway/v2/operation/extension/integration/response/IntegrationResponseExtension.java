package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationContentHandlingExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationStatusCodeExtension;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import springfox.documentation.service.ObjectVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class IntegrationResponseExtension implements IntegrationExtension<ObjectVendorExtension> {

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
    this.templates.templates(templates.templates());
    return this;
  }

  public IntegrationResponseExtension template(IntegrationResponseTemplateExtension template) {
    this.templates.template(template);
    return this;
  }

  public IntegrationResponseExtension parameters(IntegrationResponseParametersExtension parameters) {
    this.parameters.parameters(parameters.parameters());
    return this;
  }

  public IntegrationResponseExtension parameter(IntegrationResponseParameterExtension parameter) {
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

  public IntegrationResponseTemplatesExtension templates() {
    return templates;
  }

  public IntegrationResponseParametersExtension parameters() {
    return parameters;
  }

  public boolean matches(IntegrationResponseExtension response) {
    return Pattern.matches(responseStatusPattern, response.responseStatusPattern());
  }

  public IntegrationResponseExtension update(IntegrationResponseExtension response) {
    return this.statusCode(response.statusCode())
        .contentHandling(response.contentHandling())
        .templates(response.templates())
        .parameters(response.parameters());
  }

  @Override
  public ObjectVendorExtension toVendorExtension() {
    Objects.requireNonNull(responseStatusPattern);
    Objects.requireNonNull(statusCode);
    ObjectVendorExtension extension = new ObjectVendorExtension(responseStatusPattern);
    extension.addProperty(statusCode.toVendorExtension());
    if (templates.isValid()) extension.addProperty(templates.toVendorExtension());
    if (parameters.isValid()) extension.addProperty(parameters.toVendorExtension());
    if (contentHandling.isValid()) extension.addProperty(contentHandling.toVendorExtension());
    return extension;
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
