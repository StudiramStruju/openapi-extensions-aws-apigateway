package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationCredentialsExtension implements IntegrationExtension<StringVendorExtension> {

  public static final String NAME = "credentials";

  @Nullable
  private final String credentials;

  public IntegrationCredentialsExtension(@Nullable String credentials) {
    this.credentials = credentials;
  }

  @Nullable
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
    return Objects.equals(credentials, that.credentials);
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
}
