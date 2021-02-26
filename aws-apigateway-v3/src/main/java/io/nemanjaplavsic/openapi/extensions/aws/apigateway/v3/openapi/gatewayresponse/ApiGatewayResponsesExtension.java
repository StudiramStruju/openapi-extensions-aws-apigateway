package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.openapi.gatewayresponse;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.openapi.OpenApiGatewayExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.openapi.gatewayresponse.response.GatewayResponseExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ApiGatewayResponsesExtension implements OpenApiGatewayExtension<LinkedHashMap<String, LinkedHashMap<String, Object>>> {

  public static final String NAME = "x-amazon-apigateway-gateway-responses";

  private final List<GatewayResponseExtension> responses;

  public ApiGatewayResponsesExtension() {
    this.responses = new ArrayList<>();
  }

  public ApiGatewayResponsesExtension(List<GatewayResponseExtension> responses) {
    this.responses = Objects.requireNonNullElse(responses, new ArrayList<>());
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof ApiGatewayResponsesExtension)) return false;
    ApiGatewayResponsesExtension that = (ApiGatewayResponsesExtension) object;
    return Objects.equals(responses, that.responses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responses);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ApiGatewayResponsesExtension.class.getSimpleName() + "[", "]")
        .add("responses=" + responses)
        .toString();
  }

  public ApiGatewayResponsesExtension response(GatewayResponseExtension responseExtension) {
    if (responseExtension.isValid()) {
      responses.stream()
          .filter(GatewayResponseExtension::isValid)
          .filter(existing -> existing.matches(responseExtension))
          .findFirst()
          .ifPresentOrElse(
              existing -> existing.update(responseExtension),
              () -> responses.add(responseExtension));
    }
    return this;
  }

  public ApiGatewayResponsesExtension responses(List<GatewayResponseExtension> responses) {
    responses.forEach(this::response);
    return this;
  }

  public List<GatewayResponseExtension> getResponses() {
    return responses;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  @Override
  public LinkedHashMap<String, LinkedHashMap<String, Object>> getExtensionValue() {
    LinkedHashMap<String, LinkedHashMap<String, Object>> extension = new LinkedHashMap<>();
    responses.stream()
        .filter(GatewayResponseExtension::isValid)
        .forEach(response -> extension.put(response.getExtensionKey(), response.getExtensionValue()));
    return extension;
  }

  public boolean isValid() {
    try {
      Objects.requireNonNull(responses);
      return !responses.isEmpty();
    } catch (NullPointerException e) {
      return false;
    }
  }
}
