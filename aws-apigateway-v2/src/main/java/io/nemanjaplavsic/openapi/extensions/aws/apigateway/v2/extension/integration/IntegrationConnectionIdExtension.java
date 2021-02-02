package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import org.springframework.util.StringUtils;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationConnectionIdExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "connectionId";

  private String connectionId;

  public IntegrationConnectionIdExtension(String connectionId) {
    this.connectionId = connectionId;
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationConnectionIdExtension connectionId(String connectionId) {
    if (StringUtils.hasText(connectionId))
      this.connectionId = connectionId;
    return this;
  }

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
    return connectionId.equals(that.connectionId);
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

  public static class Builder {
    private String connectionId;

    Builder() {
    }

    public Builder connectionId(String connectionId) {
      this.connectionId = connectionId;
      return this;
    }

    public IntegrationConnectionIdExtension build() {
      return new IntegrationConnectionIdExtension(connectionId);
    }
  }
}
