package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import lombok.Getter;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.StringVendorExtension;

public class IntegrationResponseExtension implements IntegrationExtension<ObjectVendorExtension> {

  @Getter
  private String responseStatusPattern = "default";
  private int statusCode;
  private ContentHandling contentHandling = ContentHandling.NONE;
  private final IntegrationResponseTemplatesExtension templates = new IntegrationResponseTemplatesExtension();
  private final IntegrationResponseParametersExtension parameters = new IntegrationResponseParametersExtension();

  public IntegrationResponseExtension responseStatusPattern(String responseStatusPattern) {
    this.responseStatusPattern = responseStatusPattern;
    return this;
  }

  public IntegrationResponseExtension statusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public IntegrationResponseExtension contentHandling(ContentHandling contentHandling) {
    this.contentHandling = contentHandling;
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
    this.parameters.parameters(parameters.responseParameters());
    return this;
  }

  public IntegrationResponseExtension parameter(IntegrationResponseParameterExtension parameter) {
    this.parameters.parameter(parameter);
    return this;
  }

  @Override
  public ObjectVendorExtension toVendorExtension() {
    ObjectVendorExtension extension = new ObjectVendorExtension(responseStatusPattern);
    extension.addProperty(new StringVendorExtension("statusCode", String.valueOf(statusCode)));
    if (!ContentHandling.NONE.equals(contentHandling)) {
      extension.addProperty(new StringVendorExtension("contentHandling", contentHandling.key()));
    }
    extension.addProperty(templates.toVendorExtension());
    extension.addProperty(parameters.toVendorExtension());
    return extension;
  }
}
