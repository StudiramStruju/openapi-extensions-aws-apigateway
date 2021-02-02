package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import org.springframework.util.StringUtils;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationCredentialsExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "credentials";

  private String credentials;

  public IntegrationCredentialsExtension(String credentials) {
    this.credentials = Objects.requireNonNull(credentials);
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationCredentialsExtension credentials(String credentials) {
    if (StringUtils.hasText(credentials))
      this.credentials = credentials;
    return this;
  }

  public String credentials() {
    return credentials;
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    return new StringVendorExtension(NAME, credentials);
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(credentials);
      return StringUtils.hasText(credentials);
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationCredentialsExtension)) return false;
    IntegrationCredentialsExtension that = (IntegrationCredentialsExtension) object;
    return credentials.equals(that.credentials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(credentials);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationCredentialsExtension.class.getSimpleName() + "[", "]")
        .add("credentials='" + credentials + "'")
        .toString();
  }

  public static class Builder {
    private String credentials;

    Builder() {
    }

    public Builder credentials(String credentials) {
      this.credentials = credentials;
      return this;
    }

    public IntegrationCredentialsExtension build() {
      return new IntegrationCredentialsExtension(credentials);
    }
  }
}
