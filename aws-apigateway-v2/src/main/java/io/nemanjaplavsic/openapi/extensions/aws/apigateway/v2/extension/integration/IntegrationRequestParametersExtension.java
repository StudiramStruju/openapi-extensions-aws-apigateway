package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import springfox.documentation.service.ObjectVendorExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationRequestParametersExtension implements IntegrationExtension<ObjectVendorExtension> {

  public static final String NAME = "requestParameters";

  private final List<IntegrationRequestParameterExtension> requestParameters;

  public IntegrationRequestParametersExtension() {
    this.requestParameters = new ArrayList<>();
  }

  public IntegrationRequestParametersExtension(List<IntegrationRequestParameterExtension> requestParameters) {
    this.requestParameters = Objects.requireNonNullElse(requestParameters, new ArrayList<>());
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationRequestParametersExtension parameter(IntegrationRequestParameterExtension parameter) {
    requestParameters.stream()
        .filter(existing -> existing.matches(parameter))
        .findFirst()
        .ifPresentOrElse(
            existing -> existing.update(parameter),
            () -> requestParameters.add(parameter));
    return this;
  }

  public IntegrationRequestParametersExtension parameters(List<IntegrationRequestParameterExtension> parameters) {
    parameters.forEach(this::parameter);
    return this;
  }

  public List<IntegrationRequestParameterExtension> requestParameters() {
    return requestParameters;
  }

  @Override
  public ObjectVendorExtension toVendorExtension() {
    ObjectVendorExtension extension = new ObjectVendorExtension(NAME);
    requestParameters.stream()
        .map(IntegrationRequestParameterExtension::toVendorExtension)
        .forEach(extension::addProperty);
    return extension;
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(requestParameters);
      return !requestParameters.isEmpty();
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationRequestParametersExtension)) return false;
    IntegrationRequestParametersExtension that = (IntegrationRequestParametersExtension) object;
    return requestParameters.equals(that.requestParameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestParameters);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationRequestParametersExtension.class.getSimpleName() + "[", "]")
        .add("requestParameters=" + requestParameters)
        .toString();
  }

  public static class Builder {
    private final ArrayList<IntegrationRequestParameterExtension> requestParameters = new ArrayList<>();

    Builder() {
    }

    public Builder requestParameter(IntegrationRequestParameterExtension requestParameter) {
      this.requestParameters.add(requestParameter);
      return this;
    }

    public Builder requestParameters(Collection<? extends IntegrationRequestParameterExtension> requestParameters) {
      this.requestParameters.addAll(requestParameters);
      return this;
    }

    public Builder clearRequestParameters() {
      this.requestParameters.clear();
      return this;
    }

    public IntegrationRequestParametersExtension build() {
      return new IntegrationRequestParametersExtension(requestParameters);
    }
  }
}
