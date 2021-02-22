package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationExtension;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationResponseParametersExtension implements IntegrationExtension<LinkedHashMap<String, String>> {

  public static final String NAME = "responseParameters";

  private final List<IntegrationResponseParameterExtension> responseParameters;

  public IntegrationResponseParametersExtension() {
    this(null);
  }

  public IntegrationResponseParametersExtension(@Nullable List<IntegrationResponseParameterExtension> responseParameters) {
    this.responseParameters = Objects.requireNonNullElse(responseParameters, new ArrayList<>());
  }

  public IntegrationResponseParametersExtension parameter(IntegrationResponseParameterExtension parameter) {
    if (parameter.isValid()) {
      responseParameters.stream()
          .filter(IntegrationResponseParameterExtension::isValid)
          .filter(existing -> existing.matches(parameter))
          .findFirst()
          .ifPresentOrElse(
              existing -> {
                responseParameters.remove(existing);
                responseParameters.add(parameter);
              },
              () -> responseParameters.add(parameter));
    }
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
  public String getExtensionKey() {
    return NAME;
  }

  @Override
  public LinkedHashMap<String, String> getExtensionValue() {
    LinkedHashMap<String, String> extension = new LinkedHashMap<>();
    responseParameters.stream()
        .filter(IntegrationResponseParameterExtension::isValid)
        .forEach(property -> extension.put(property.getExtensionKey(), property.getExtensionValue()));
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
}
