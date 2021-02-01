package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@EqualsAndHashCode
@ToString
public class AwsProperties {

  private final ApiGatewayProperties apiGateway;

  @ConstructorBinding
  public AwsProperties(ApiGatewayProperties apiGateway) {
    this.apiGateway = apiGateway;
  }


}
