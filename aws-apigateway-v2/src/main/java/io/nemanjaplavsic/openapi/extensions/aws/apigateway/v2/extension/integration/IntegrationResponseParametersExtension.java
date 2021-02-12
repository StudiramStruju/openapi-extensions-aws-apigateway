package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import springfox.documentation.service.ObjectVendorExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationResponseParametersExtension implements IntegrationExtension<ObjectVendorExtension> {

  public static final String NAME = "responseParameters";

  private final List<IntegrationResponseParameterExtension> responseParameters;

  public IntegrationResponseParametersExtension() {
    this.responseParameters = new ArrayList<>();
  }

  public IntegrationResponseParametersExtension(List<IntegrationResponseParameterExtension> responseParameters) {
    this.responseParameters = Objects.requireNonNullElse(responseParameters, new ArrayList<>());
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationResponseParametersExtension parameter(IntegrationResponseParameterExtension parameter) {
    responseParameters.stream()
        .filter(existing -> existing.matches(parameter))
        .findFirst()
        .ifPresentOrElse(
            existing -> existing.update(parameter),
            () -> responseParameters.add(parameter));
    return this;
  }

  public IntegrationResponseParametersExtension parameters(List<IntegrationResponseParameterExtension> parameters) {
    parameters.forEach(this::parameter);
    return this;
  }

  public List<IntegrationResponseParameterExtension> parameters() {
    return responseParameters;
  }

  @Override
  public ObjectVendorExtension toVendorExtension() {
    ObjectVendorExtension extension = new ObjectVendorExtension(NAME);
    responseParameters.stream()
        .filter(IntegrationResponseParameterExtension::isValid)
        .map(IntegrationResponseParameterExtension::toVendorExtension)
        .forEach(extension::addProperty);
    return extension;
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(responseParameters);
      return !responseParameters.isEmpty();
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationResponseParametersExtension)) return false;
    IntegrationResponseParametersExtension that = (IntegrationResponseParametersExtension) object;
    return responseParameters.equals(that.responseParameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseParameters);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationResponseParametersExtension.class.getSimpleName() + "[", "]")
        .add("responseParameters=" + responseParameters)
        .toString();
  }

  public static class Builder {
    private final ArrayList<IntegrationResponseParameterExtension> responseParameters = new ArrayList<>();

    Builder() {
    }

    public Builder responseParameter(IntegrationResponseParameterExtension responseParameter) {
      this.responseParameters.add(responseParameter);
      return this;
    }

    public Builder responseParameters(Collection<? extends IntegrationResponseParameterExtension> responseParameters) {
      this.responseParameters.addAll(responseParameters);
      return this;
    }

    public Builder clearResponseParameters() {
      this.responseParameters.clear();
      return this;
    }

    public IntegrationResponseParametersExtension build() {
      return new IntegrationResponseParametersExtension(responseParameters);
    }
  }
}
