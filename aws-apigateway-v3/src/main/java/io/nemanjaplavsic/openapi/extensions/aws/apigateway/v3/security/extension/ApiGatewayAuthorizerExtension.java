package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security.extension;

import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ApiGatewayAuthorizerExtension implements ApiGatewaySecurityExtension<LinkedHashMap<String, Object>> {

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
  public String getExtensionKey() {
    return NAME;
  }

  @Override
  public LinkedHashMap<String, Object> getExtensionValue() {
    final LinkedHashMap<String, Object> extension = new LinkedHashMap<>();
    extension.put("type", typeName);
    extension.put("providerARNs", providerArns.stream().collect(Collectors.toUnmodifiableList()));
    return extension;
  }
}
