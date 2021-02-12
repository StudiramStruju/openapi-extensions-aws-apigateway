package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@EqualsAndHashCode
@ToString
@ConfigurationProperties(prefix = "openapi.extension.aws.api-gateway")
public class ApiGatewayProperties {

  /**
   * Enables default operation plugin
   */
  private final boolean enabledOperationPlugin;
  private final ApiGatewayServiceProperties service;

  @ConstructorBinding
  public ApiGatewayProperties(boolean enabledOperationPlugin, ApiGatewayServiceProperties service) {
    this.enabledOperationPlugin = enabledOperationPlugin;
    this.service = service;
  }

}
