package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.lang.Nullable;

@Getter
@EqualsAndHashCode
@ToString
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
}
