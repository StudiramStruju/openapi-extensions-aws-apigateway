package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationExtension;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationResponseTemplatesExtension implements IntegrationExtension<LinkedHashMap<String, String>> {

  public static final String NAME = "responseTemplates";

  private final List<IntegrationResponseTemplateExtension> templates;

  public IntegrationResponseTemplatesExtension() {
    this(null);
  }

  public IntegrationResponseTemplatesExtension(@Nullable List<IntegrationResponseTemplateExtension> templates) {
    this.templates = Objects.requireNonNullElse(templates, new ArrayList<>());
  }

  public IntegrationResponseTemplatesExtension template(IntegrationResponseTemplateExtension template) {
    if (template.isValid()) {
      templates.stream()
          .filter(IntegrationResponseTemplateExtension::isValid)
          .filter(existing -> existing.matches(template))
          .findFirst()
          .ifPresentOrElse(
              existing -> {
                templates.remove(existing);
                templates.add(template);
              },
              () -> templates.add(template));
    }
    return this;
  }

  public IntegrationResponseTemplatesExtension templates(List<IntegrationResponseTemplateExtension> templates) {
    templates.forEach(this::template);
    return this;
  }

  public List<IntegrationResponseTemplateExtension> templates() {
    return templates;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  @Override
  public LinkedHashMap<String, String> getExtensionValue() {
    LinkedHashMap<String, String> extension = new LinkedHashMap<>();
    templates.stream()
        .filter(IntegrationResponseTemplateExtension::isValid)
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
    if (!(object instanceof IntegrationResponseTemplatesExtension)) return false;
    IntegrationResponseTemplatesExtension that = (IntegrationResponseTemplatesExtension) object;
    return templates.equals(that.templates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templates);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationResponseTemplatesExtension.class.getSimpleName() + "[", "]")
        .add("templates=" + templates)
        .toString();
  }
}
