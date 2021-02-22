package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.security.extension;

import org.springframework.lang.Nullable;
import springfox.documentation.service.ListVendorExtension;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.StringVendorExtension;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ApiGatewayAuthorizerExtension implements ApiGatewaySecurityExtension<ObjectVendorExtension> {

  public static final String NAME = "x-amazon-apigateway-authorizer";

  private final String typeName;
  private final Set<String> providerArns;

  public ApiGatewayAuthorizerExtension(String typeName, @Nullable Set<String> providerArns) {
    this.typeName = Objects.requireNonNull(typeName, "ApiGatewayAuthorizerExtension typeName should not be null!");
    this.providerArns = Objects.requireNonNullElse(providerArns, new HashSet<>());
  }

  public static ApiGatewayAuthorizerExtension cognitoUserPools(@Nullable Set<String> providerArns) {
    return new ApiGatewayAuthorizerExtension("cognito_user_pools", providerArns);
  }

  public ApiGatewayAuthorizerExtension addProviderArn(String providerArn) {
    this.providerArns.add(providerArn);
    return this;
  }

  public String type() {
    return typeName;
  }

  public Set<String> providerArns() {
    return providerArns;
  }

  @Override
  public ObjectVendorExtension toVendorExtension() {
    ObjectVendorExtension extension = new ObjectVendorExtension(NAME);
    extension.addProperty(new StringVendorExtension("type", typeName));
    extension.addProperty(new ListVendorExtension<String>("providerARNs", providerArns.stream().collect(Collectors.toUnmodifiableList())));
    return extension;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof ApiGatewayAuthorizerExtension)) return false;
    ApiGatewayAuthorizerExtension that = (ApiGatewayAuthorizerExtension) object;
    return typeName.equals(that.typeName) &&
        providerArns.equals(that.providerArns);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeName, providerArns);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ApiGatewayAuthorizerExtension.class.getSimpleName() + "[", "]")
        .add("typeName='" + typeName + "'")
        .add("providerArns=" + providerArns)
        .toString();
  }
}
