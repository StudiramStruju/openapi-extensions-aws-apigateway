package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration;


import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.request.IntegrationRequestParameterExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationRequestParametersExtension implements ApiGatewayIntegrationExtension<LinkedHashMap<String, String>> {

  public static final String NAME = "requestParameters";

  private final List<IntegrationRequestParameterExtension> parameters;

  public IntegrationRequestParametersExtension() {
    this.parameters = new ArrayList<>();
  }

  public IntegrationRequestParametersExtension(List<IntegrationRequestParameterExtension> parameters) {
    this.parameters = Objects.requireNonNullElse(parameters, new ArrayList<>());
  }

  public IntegrationRequestParametersExtension parameter(IntegrationRequestParameterExtension parameter) {
    if (parameter.isValid()) {
      parameters.stream()
          .filter(IntegrationRequestParameterExtension::isValid)
          .filter(existing -> existing.matches(parameter))
          .findFirst()
          .ifPresentOrElse(
              existing -> {
                parameters.remove(existing);
                parameters.add(parameter);
              },
              () -> parameters.add(parameter));
    }
    return this;
  }

  public IntegrationRequestParametersExtension parameters(List<IntegrationRequestParameterExtension> parameters) {
    parameters.forEach(this::parameter);
    return this;
  }

  public List<IntegrationRequestParameterExtension> getParameters() {
    return parameters;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  @Override
  public LinkedHashMap<String, String> getExtensionValue() {
    final LinkedHashMap<String, String> extension = new LinkedHashMap<>();
    parameters.stream()
        .filter(IntegrationRequestParameterExtension::isValid)
        .forEach(property -> extension.put(property.getExtensionKey(), property.getExtensionValue()));
    return extension;
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(parameters);
      return !parameters.isEmpty();
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationRequestParametersExtension)) return false;
    IntegrationRequestParametersExtension that = (IntegrationRequestParametersExtension) object;
    return parameters.equals(that.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parameters);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationRequestParametersExtension.class.getSimpleName() + "[", "]")
        .add("requestParameters=" + parameters)
        .toString();
  }
}
