package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Objects;
import java.util.StringJoiner;

@ConfigurationProperties(prefix = "openapi.extension")
public class ExtensionProperties {

  private final AwsProperties aws;

  @ConstructorBinding
  public ExtensionProperties(AwsProperties aws) {
    this.aws = aws;
  }

  public AwsProperties getAws() {
    return aws;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof ExtensionProperties)) return false;
    ExtensionProperties that = (ExtensionProperties) object;
    return getAws().equals(that.getAws());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAws());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ExtensionProperties.class.getSimpleName() + "[", "]")
        .add("aws=" + aws)
        .toString();
  }
}
