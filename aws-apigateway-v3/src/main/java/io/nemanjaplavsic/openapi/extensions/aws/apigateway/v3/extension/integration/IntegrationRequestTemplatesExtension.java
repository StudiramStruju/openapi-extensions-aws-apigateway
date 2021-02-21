package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration.request.IntegrationRequestTemplateExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationRequestTemplatesExtension implements IntegrationExtension<LinkedHashMap<String, String>> {

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
  public String getExtensionKey() {
    return NAME;
  }

  @Override
  public LinkedHashMap<String, String> getExtensionValue() {
    final LinkedHashMap<String, String> extension = new LinkedHashMap<>();
    templates.stream()
        .filter(IntegrationRequestTemplateExtension::isValid)
        .forEach(property -> extension.put(property.getExtensionKey(), property.getExtensionValue()));
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
