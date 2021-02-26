package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Objects;
import java.util.StringJoiner;

@ConfigurationProperties(prefix = "openapi.extension.aws")
public class AwsProperties {

  private final ApiGatewayProperties apiGateway;

  @ConstructorBinding
  public AwsProperties(ApiGatewayProperties apiGateway) {
    this.apiGateway = apiGateway;
  }

  public ApiGatewayProperties getApiGateway() {
    return apiGateway;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof AwsProperties)) return false;
    AwsProperties that = (AwsProperties) object;
    return getApiGateway().equals(that.getApiGateway());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getApiGateway());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", AwsProperties.class.getSimpleName() + "[", "]")
        .add("apiGateway=" + apiGateway)
        .toString();
  }
}
