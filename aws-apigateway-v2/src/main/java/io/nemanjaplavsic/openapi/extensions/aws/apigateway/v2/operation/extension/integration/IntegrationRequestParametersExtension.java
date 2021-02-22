package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.request.IntegrationRequestParameterExtension;
import springfox.documentation.service.ObjectVendorExtension;

import java.util.ArrayList;
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

  public IntegrationRequestParametersExtension parameter(IntegrationRequestParameterExtension parameter) {
    if (parameter.isValid()) {
      requestParameters.stream()
          .filter(IntegrationRequestParameterExtension::isValid)
          .filter(existing -> existing.matches(parameter))
          .findFirst()
          .ifPresentOrElse(
              existing -> {
                requestParameters.remove(existing);
                requestParameters.add(parameter);
              },
              () -> requestParameters.add(parameter));
    }
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
        .filter(IntegrationRequestParameterExtension::isValid)
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
}
