package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@EqualsAndHashCode
@ToString
public class ExtensionProperties {

  private final AwsProperties aws;

  @ConstructorBinding
  public ExtensionProperties(AwsProperties aws) {
    this.aws = aws;
  }
}
