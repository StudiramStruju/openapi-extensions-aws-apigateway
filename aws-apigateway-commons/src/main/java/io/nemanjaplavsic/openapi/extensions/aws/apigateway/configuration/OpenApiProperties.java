package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@EqualsAndHashCode
@ToString
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

}
