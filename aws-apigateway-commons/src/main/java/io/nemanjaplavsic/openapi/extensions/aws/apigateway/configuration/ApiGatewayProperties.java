package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@EqualsAndHashCode
@ToString
public class ApiGatewayProperties {

  private final ApiGatewayServiceProperties service;

  @ConstructorBinding
  public ApiGatewayProperties(ApiGatewayServiceProperties service) {
    this.service = service;
  }

}
