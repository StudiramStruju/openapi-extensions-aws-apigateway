package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.openapi.gatewayresponse.response.parameter;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ConvertableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ValidatableExtension;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class GatewayResponseParametersExtension implements ConvertableExtension<LinkedHashMap<String, String>>, ValidatableExtension {

  public static final String NAME = "responseParameters";

  private final List<GatewayResponseParameterExtension> parameters;

  public GatewayResponseParametersExtension() {
    this(null);
  }

  public GatewayResponseParametersExtension(@Nullable List<GatewayResponseParameterExtension> parameters) {
    this.parameters = Objects.requireNonNullElse(parameters, new ArrayList<>());
  }

  public GatewayResponseParametersExtension parameter(GatewayResponseParameterExtension parameter) {
    if (parameter.isValid()) {
      parameters.stream()
          .filter(GatewayResponseParameterExtension::isValid)
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

  public GatewayResponseParametersExtension parameters(List<GatewayResponseParameterExtension> parameters) {
    parameters.forEach(this::parameter);
    return this;
  }

  public List<GatewayResponseParameterExtension> getParameters() {
    return parameters;
  }


  @Override
  public String getExtensionKey() {
    return NAME;
  }

  @Override
  public LinkedHashMap<String, String> getExtensionValue() {
    LinkedHashMap<String, String> extension = new LinkedHashMap<>();
    parameters.stream()
        .filter(GatewayResponseParameterExtension::isValid)
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
    if (!(object instanceof GatewayResponseParametersExtension)) return false;
    GatewayResponseParametersExtension that = (GatewayResponseParametersExtension) object;
    return parameters.equals(that.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parameters);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GatewayResponseParametersExtension.class.getSimpleName() + "[", "]")
        .add("responseParameters=" + parameters)
        .toString();
  }
}
