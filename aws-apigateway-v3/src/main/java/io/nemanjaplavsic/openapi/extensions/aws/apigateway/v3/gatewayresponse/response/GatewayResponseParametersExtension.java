package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.gatewayresponse.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.gatewayresponse.ResponseExtension;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class GatewayResponseParametersExtension implements ResponseExtension<LinkedHashMap<String, String>> {

  public static final String NAME = "responseParameters";

  private final List<GatewayResponseParameterExtension> responseParameters;

  public GatewayResponseParametersExtension() {
    this(null);
  }

  public GatewayResponseParametersExtension(@Nullable List<GatewayResponseParameterExtension> responseParameters) {
    this.responseParameters = Objects.requireNonNullElse(responseParameters, new ArrayList<>());
  }

  public GatewayResponseParametersExtension parameter(GatewayResponseParameterExtension parameter) {
    if (parameter.isValid()) {
      responseParameters.stream()
          .filter(GatewayResponseParameterExtension::isValid)
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

  public GatewayResponseParametersExtension parameters(List<GatewayResponseParameterExtension> parameters) {
    parameters.forEach(this::parameter);
    return this;
  }

  public List<GatewayResponseParameterExtension> parameters() {
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
        .filter(GatewayResponseParameterExtension::isValid)
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
    if (!(object instanceof GatewayResponseParametersExtension)) return false;
    GatewayResponseParametersExtension that = (GatewayResponseParametersExtension) object;
    return responseParameters.equals(that.responseParameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseParameters);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GatewayResponseParametersExtension.class.getSimpleName() + "[", "]")
        .add("responseParameters=" + responseParameters)
        .toString();
  }
}
