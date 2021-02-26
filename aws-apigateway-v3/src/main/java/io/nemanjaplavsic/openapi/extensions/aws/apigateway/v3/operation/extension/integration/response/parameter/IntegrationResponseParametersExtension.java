package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response.parameter;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ConvertableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ValidatableExtension;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationResponseParametersExtension implements ConvertableExtension<LinkedHashMap<String, String>>, ValidatableExtension {

  public static final String NAME = "responseParameters";

  private final List<IntegrationResponseParameterExtension> parameters;

  public IntegrationResponseParametersExtension() {
    this(null);
  }

  public IntegrationResponseParametersExtension(@Nullable List<IntegrationResponseParameterExtension> parameters) {
    this.parameters = Objects.requireNonNullElse(parameters, new ArrayList<>());
  }

  public IntegrationResponseParametersExtension parameter(IntegrationResponseParameterExtension parameter) {
    if (parameter.isValid()) {
      parameters.stream()
          .filter(IntegrationResponseParameterExtension::isValid)
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

  public IntegrationResponseParametersExtension parameters(List<IntegrationResponseParameterExtension> parameters) {
    parameters.forEach(this::parameter);
    return this;
  }

  public List<IntegrationResponseParameterExtension> getParameters() {
    return parameters;
  }

  public String getExtensionKey() {
    return NAME;
  }

  public LinkedHashMap<String, String> getExtensionValue() {
    LinkedHashMap<String, String> extension = new LinkedHashMap<>();
    parameters.stream()
        .filter(IntegrationResponseParameterExtension::isValid)
        .forEach(property -> extension.put(property.getExtensionKey(), property.getExtensionValue()));
    return extension;
  }

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
    if (!(object instanceof IntegrationResponseParametersExtension)) return false;
    IntegrationResponseParametersExtension that = (IntegrationResponseParametersExtension) object;
    return parameters.equals(that.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parameters);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationResponseParametersExtension.class.getSimpleName() + "[", "]")
        .add("responseParameters=" + parameters)
        .toString();
  }
}
