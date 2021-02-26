package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Objects;
import java.util.StringJoiner;

@ConfigurationProperties(prefix = "openapi")
public class OpenApiProperties {

  private final boolean enabled;
  private final ApiInfoProperties apiInfo;
  private final ExtensionProperties extension;

  @ConstructorBinding
  public OpenApiProperties(boolean enabled, ApiInfoProperties apiInfo, ExtensionProperties extension) {
    this.enabled = enabled;
    this.apiInfo = apiInfo;
    this.extension = extension;
  }

  public ApiInfoProperties getApiInfo() {
    return apiInfo;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public ExtensionProperties getExtension() {
    return extension;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof OpenApiProperties)) return false;
    OpenApiProperties that = (OpenApiProperties) object;
    return isEnabled() == that.isEnabled() &&
        getApiInfo().equals(that.getApiInfo()) &&
        getExtension().equals(that.getExtension());
  }

  @Override
  public int hashCode() {
    return Objects.hash(isEnabled(), getApiInfo(), getExtension());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", OpenApiProperties.class.getSimpleName() + "[", "]")
        .add("enabled=" + enabled)
        .add("apiInfo=" + apiInfo)
        .add("extension=" + extension)
        .toString();
  }
}
