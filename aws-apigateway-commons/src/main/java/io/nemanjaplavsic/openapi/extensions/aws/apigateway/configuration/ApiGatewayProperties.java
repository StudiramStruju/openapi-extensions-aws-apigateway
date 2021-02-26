package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Objects;
import java.util.StringJoiner;

@ConfigurationProperties(prefix = "openapi.extension.aws.api-gateway")
public class ApiGatewayProperties {

  /**
   * Enables default operation plugin
   */
  private final boolean enableOperationPlugin;
  private final ApiGatewayServiceProperties service;

  @ConstructorBinding
  public ApiGatewayProperties(boolean enableOperationPlugin, ApiGatewayServiceProperties service) {
    this.enableOperationPlugin = enableOperationPlugin;
    this.service = service;
  }

  public boolean isEnableOperationPlugin() {
    return enableOperationPlugin;
  }

  public ApiGatewayServiceProperties getService() {
    return service;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof ApiGatewayProperties)) return false;
    ApiGatewayProperties that = (ApiGatewayProperties) object;
    return isEnableOperationPlugin() == that.isEnableOperationPlugin() &&
        getService().equals(that.getService());
  }

  @Override
  public int hashCode() {
    return Objects.hash(isEnableOperationPlugin(), getService());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ApiGatewayProperties.class.getSimpleName() + "[", "]")
        .add("enableOperationPlugin=" + enableOperationPlugin)
        .add("service=" + service)
        .toString();
  }
}
