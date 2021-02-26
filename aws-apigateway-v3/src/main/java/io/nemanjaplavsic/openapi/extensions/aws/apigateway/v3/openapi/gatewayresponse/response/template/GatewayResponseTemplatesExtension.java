package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.openapi.gatewayresponse.response.template;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class GatewayResponseTemplatesExtension {

  public static final String NAME = "responseTemplates";

  private final List<GatewayResponseTemplateExtension> templates;

  public GatewayResponseTemplatesExtension() {
    this(null);
  }

  public GatewayResponseTemplatesExtension(@Nullable List<GatewayResponseTemplateExtension> templates) {
    this.templates = Objects.requireNonNullElse(templates, new ArrayList<>());
  }

  public GatewayResponseTemplatesExtension template(GatewayResponseTemplateExtension template) {
    if (template.isValid()) {
      templates.stream()
          .filter(GatewayResponseTemplateExtension::isValid)
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

  public GatewayResponseTemplatesExtension templates(List<GatewayResponseTemplateExtension> templates) {
    templates.forEach(this::template);
    return this;
  }

  public List<GatewayResponseTemplateExtension> getTemplates() {
    return templates;
  }

  public String getExtensionKey() {
    return NAME;
  }

  public LinkedHashMap<String, String> getExtensionValue() {
    LinkedHashMap<String, String> extension = new LinkedHashMap<>();
    templates.stream()
        .filter(GatewayResponseTemplateExtension::isValid)
        .forEach(property -> extension.put(property.getExtensionKey(), property.getExtensionValue()));
    return extension;
  }

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
    if (!(object instanceof GatewayResponseTemplatesExtension)) return false;
    GatewayResponseTemplatesExtension that = (GatewayResponseTemplatesExtension) object;
    return templates.equals(that.templates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templates);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GatewayResponseTemplatesExtension.class.getSimpleName() + "[", "]")
        .add("templates=" + templates)
        .toString();
  }
}
