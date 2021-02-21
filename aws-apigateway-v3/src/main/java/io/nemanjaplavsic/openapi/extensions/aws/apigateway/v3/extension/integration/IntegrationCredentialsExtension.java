package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationCredentialsExtension implements IntegrationExtension<String> {

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
  public String getExtensionKey() {
    return NAME;
  }

  public String getExtensionValue() {
    return credentials;
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
