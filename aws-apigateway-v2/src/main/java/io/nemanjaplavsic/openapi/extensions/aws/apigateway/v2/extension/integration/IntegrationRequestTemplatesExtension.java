package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.request.IntegrationRequestTemplateExtension;
import springfox.documentation.service.ObjectVendorExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationRequestTemplatesExtension implements IntegrationExtension<ObjectVendorExtension> {

  public static final String NAME = "requestTemplates";

  private final List<IntegrationRequestTemplateExtension> templates;

  public IntegrationRequestTemplatesExtension() {
    this.templates = new ArrayList<>();
  }

  public IntegrationRequestTemplatesExtension(List<IntegrationRequestTemplateExtension> templates) {
    this.templates = Objects.requireNonNullElse(templates, new ArrayList<>());
  }

  public IntegrationRequestTemplatesExtension template(IntegrationRequestTemplateExtension template) {
    if (template.isValid()) {
      templates.stream()
          .filter(IntegrationRequestTemplateExtension::isValid)
          .filter(existing -> existing.matches(template))
          .findFirst()
          .ifPresentOrElse(
              existing -> existing = template,
              () -> templates.add(template));
    }
    return this;
  }

  public IntegrationRequestTemplatesExtension templates(List<IntegrationRequestTemplateExtension> templates) {
    templates.forEach(this::template);
    return this;
  }

  @Override
  public ObjectVendorExtension toVendorExtension() {
    ObjectVendorExtension extension = new ObjectVendorExtension(NAME);
    templates.stream()
        .map(IntegrationRequestTemplateExtension::toVendorExtension)
        .forEach(extension::addProperty);
    return extension;
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(templates);
      return !templates.isEmpty();
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationRequestTemplatesExtension)) return false;
    IntegrationRequestTemplatesExtension that = (IntegrationRequestTemplatesExtension) object;
    return templates.equals(that.templates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templates);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationRequestTemplatesExtension.class.getSimpleName() + "[", "]")
        .add("templates=" + templates)
        .toString();
  }
}
