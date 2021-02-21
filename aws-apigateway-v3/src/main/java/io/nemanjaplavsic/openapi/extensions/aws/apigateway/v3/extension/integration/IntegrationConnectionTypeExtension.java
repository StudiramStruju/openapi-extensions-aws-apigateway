package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ConnectionType;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationConnectionTypeExtension implements IntegrationExtension<String> {

  public static final String NAME = "connectionType";

  private ConnectionType connectionType;

  public IntegrationConnectionTypeExtension() {
    this(null);
  }

  public IntegrationConnectionTypeExtension(@Nullable ConnectionType connectionType) {
    this.connectionType = Objects.requireNonNullElse(connectionType, ConnectionType.NONE);
  }

  public IntegrationConnectionTypeExtension connectionType(ConnectionType connectionType) {
    this.connectionType = Objects.requireNonNullElse(connectionType, ConnectionType.NONE);
    return this;
  }

  public ConnectionType connectionType() {
    return connectionType;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  public String getExtensionValue() {
    return connectionType.key();
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
}
