package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension;

import springfox.documentation.service.ObjectVendorExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationResponseTemplatesExtension implements IntegrationExtension<ObjectVendorExtension> {

  public static final String NAME = "responseTemplates";

  private final List<IntegrationResponseTemplateExtension> templates;

  public IntegrationResponseTemplatesExtension() {
    this.templates = new ArrayList<>();
  }

  public IntegrationResponseTemplatesExtension(List<IntegrationResponseTemplateExtension> templates) {
    this.templates = Objects.requireNonNullElse(templates, new ArrayList<>());
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationResponseTemplatesExtension template(IntegrationResponseTemplateExtension template) {
    templates.stream()
        .filter(existing -> existing.matches(template))
        .findFirst()
        .ifPresentOrElse(
            existing -> existing.update(template),
            () -> templates.add(template));
    return this;
  }

  public IntegrationResponseTemplatesExtension templates(List<IntegrationResponseTemplateExtension> templates) {
    templates.forEach(this::template);
    return this;
  }

  @Override
  public ObjectVendorExtension toVendorExtension() {
    ObjectVendorExtension extension = new ObjectVendorExtension(NAME);
    templates.stream()
        .map(IntegrationResponseTemplateExtension::toVendorExtension)
        .forEach(extension::addProperty);
    return extension;
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

  public static class Builder {
    private final ArrayList<IntegrationResponseTemplateExtension> templates = new ArrayList<>();

    Builder() {
    }

    public Builder template(IntegrationResponseTemplateExtension template) {
      this.templates.add(template);
      return this;
    }

    public Builder templates(Collection<? extends IntegrationResponseTemplateExtension> templates) {
      this.templates.addAll(templates);
      return this;
    }

    public Builder clearTemplates() {
      this.templates.clear();
      return this;
    }

    public IntegrationResponseTemplatesExtension build() {
      return new IntegrationResponseTemplatesExtension(templates);
    }
  }
}
