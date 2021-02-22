package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationConnectionIdExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "connectionId";

  @Nullable
  private final String connectionId;

  public IntegrationConnectionIdExtension(String connectionId) {
    this.connectionId = connectionId;
  }

  @Nullable
  public String connectionId() {
    return connectionId;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, connectionId);
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(connectionId);
      return StringUtils.hasText(connectionId);
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationConnectionIdExtension)) return false;
    IntegrationConnectionIdExtension that = (IntegrationConnectionIdExtension) object;
    return Objects.equals(connectionId, that.connectionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(connectionId);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationConnectionIdExtension.class.getSimpleName() + "[", "]")
        .add("connectionId='" + connectionId + "'")
        .toString();
  }
}
