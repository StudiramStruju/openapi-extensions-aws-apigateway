package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.openapi.gatewayresponse.response.status;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ConvertableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ValidatableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationStatusCodeExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class GatewayResponseStatusCodeExtension implements ConvertableExtension<String>, ValidatableExtension {

  public static final String NAME = "statusCode";

  private final Integer statusCode;

  public GatewayResponseStatusCodeExtension(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  public String getExtensionValue() {
    return statusCode.toString();
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(statusCode);
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationStatusCodeExtension)) return false;
    IntegrationStatusCodeExtension that = (IntegrationStatusCodeExtension) object;
    return statusCode.equals(that.getStatusCode());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationStatusCodeExtension.class.getSimpleName() + "[", "]")
        .add("statusCode=" + statusCode)
        .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode);
  }
}
