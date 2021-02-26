package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

@ConfigurationProperties(prefix = "openapi.api-info")
public class ApiInfoProperties {
  @Nullable
  private final String host;
  @Nullable
  private final String title;
  @Nullable
  private final String version;

  @ConstructorBinding
  public ApiInfoProperties(@Nullable String host, @Nullable String title, @Nullable String version) {
    this.host = host;
    this.title = title;
    this.version = version;
  }

  @Nullable
  public String getHost() {
    return host;
  }

  @Nullable
  public String getTitle() {
    return title;
  }

  @Nullable
  public String getVersion() {
    return version;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof ApiInfoProperties)) return false;
    ApiInfoProperties that = (ApiInfoProperties) object;
    return Objects.equals(getHost(), that.getHost()) &&
        Objects.equals(getTitle(), that.getTitle()) &&
        Objects.equals(getVersion(), that.getVersion());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getHost(), getTitle(), getVersion());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ApiInfoProperties.class.getSimpleName() + "[", "]")
        .add("host='" + host + "'")
        .add("title='" + title + "'")
        .add("version='" + version + "'")
        .toString();
  }
}
