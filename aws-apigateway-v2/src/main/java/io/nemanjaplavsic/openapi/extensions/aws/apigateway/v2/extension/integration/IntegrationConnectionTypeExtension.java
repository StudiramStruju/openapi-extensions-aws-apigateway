package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ConnectionType;
import org.springframework.lang.Nullable;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationConnectionTypeExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "connectionType";

  private ConnectionType connectionType;

  public IntegrationConnectionTypeExtension() {
  }

  public IntegrationConnectionTypeExtension(@Nullable ConnectionType connectionType) {
    this.connectionType = Objects.requireNonNullElse(connectionType, ConnectionType.NONE);
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationConnectionTypeExtension connectionType(ConnectionType connectionType) {
    this.connectionType = Objects.requireNonNullElse(connectionType, ConnectionType.NONE);
    return this;
  }

  public ConnectionType connectionType() {
    return connectionType;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, connectionType.key());
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(connectionType);
      Objects.requireNonNull(connectionType.key());
      return true;
    } catch (NullPointerException e) {
      // maybe log something
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationConnectionTypeExtension)) return false;
    IntegrationConnectionTypeExtension that = (IntegrationConnectionTypeExtension) object;
    return connectionType == that.connectionType;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationConnectionTypeExtension.class.getSimpleName() + "[", "]")
        .add("connectionType=" + connectionType)
        .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(connectionType);
  }

  public static class Builder {
    private ConnectionType connectionType;

    Builder() {
      connectionType = ConnectionType.NONE;
    }

    public Builder connectionType(ConnectionType connectionType) {
      this.connectionType = connectionType;
      return this;
    }

    public IntegrationConnectionTypeExtension build() {
      return new IntegrationConnectionTypeExtension(connectionType);
    }
  }
}
