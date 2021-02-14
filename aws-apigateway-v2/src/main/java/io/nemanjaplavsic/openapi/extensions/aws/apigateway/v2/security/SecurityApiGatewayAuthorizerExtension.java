package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.security;

import springfox.documentation.service.ListVendorExtension;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public class SecurityApiGatewayAuthorizerExtension {

  public static final String NAME = "x-amazon-apigateway-authorizer";

  private final String type;
  private final Set<String> providerArns;

  public SecurityApiGatewayAuthorizerExtension(String type, Set<String> providerArns) {
    this.type = type;
    this.providerArns = providerArns;
  }

  public SecurityApiGatewayAuthorizerExtension providerArn(String providerArn) {
    this.providerArns.add(providerArn);
    return this;
  }

  public String type() {
    return type;
  }

  public Set<String> providerArns() {
    return providerArns;
  }

  public VendorExtension<?> toVendorExtension() {
    ObjectVendorExtension extension = new ObjectVendorExtension(NAME);
    extension.addProperty(new StringVendorExtension("type", type));
    extension.addProperty(new ListVendorExtension("providerARNs", new ArrayList<>(providerArns)));
    return extension;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof SecurityApiGatewayAuthorizerExtension)) return false;
    SecurityApiGatewayAuthorizerExtension that = (SecurityApiGatewayAuthorizerExtension) object;
    return Objects.equals(type, that.type) &&
        Objects.equals(providerArns, that.providerArns);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, providerArns);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SecurityApiGatewayAuthorizerExtension.class.getSimpleName() + "[", "]")
        .add("type='" + type + "'")
        .add("providerArns=" + providerArns)
        .toString();
  }
}
