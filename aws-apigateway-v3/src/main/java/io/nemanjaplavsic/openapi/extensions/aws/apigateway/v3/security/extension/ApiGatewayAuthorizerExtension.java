package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.security.extension;

import org.springframework.lang.Nullable;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;

public class ApiGatewayAuthorizerExtension implements ApiGatewaySecurityExtension<LinkedHashMap<String, Object>> {

  public static final String NAME = "x-amazon-apigateway-authorizer";

  private final LinkedHashMap<String, Object> properties;

  public ApiGatewayAuthorizerExtension() {
    this.properties = new LinkedHashMap<>();
  }

  public static ApiGatewayAuthorizerExtension cognitoUserPools(Set<String> providerArns) {
    return new ApiGatewayAuthorizerExtension().type("cognito_user_pools").providerArns(providerArns);
  }

  public ApiGatewayAuthorizerExtension type(String type) {
    properties.put("type", type);
    return this;
  }

  public ApiGatewayAuthorizerExtension providerArns(Set<String> providerArns) {
    properties.put("providerArns", providerArns);
    return this;
  }

  public ApiGatewayAuthorizerExtension authorizerUri(String authorizerUri) {
    properties.put("authorizerUri", authorizerUri);
    return this;
  }

  public ApiGatewayAuthorizerExtension authorizerCredentials(String authorizerCredentials) {
    properties.put("authorizerCredentials", authorizerCredentials);
    return this;
  }

  public ApiGatewayAuthorizerExtension identitySource(Set<String> identitySource) {
    properties.put("identitySource", identitySource);
    return this;
  }

  public ApiGatewayAuthorizerExtension identityValidationExpression(String identityValidationExpression) {
    properties.put("identityValidationExpression", identityValidationExpression);
    return this;
  }

  public ApiGatewayAuthorizerExtension authorizerResultTtlInSeconds(String authorizerResultTtlInSeconds) {
    properties.put("authorizerResultTtlInSeconds", authorizerResultTtlInSeconds);
    return this;
  }

  public ApiGatewayAuthorizerExtension property(String name, Object value) {
    this.properties.put(name, value);
    return this;
  }

  @Nullable
  public String getType() {
    return (String) this.properties.get("type");
  }

  @Nullable
  public Set<String> getProviderArns() {
    return (Set<String>) this.properties.get("providerArns");
  }

  @Nullable
  public String getAuthorizerUri() {
    return (String) this.properties.get("authorizerUri");
  }

  @Nullable
  public String getAuthorizerCredentials() {
    return (String) this.properties.get("authorizerCredentials");
  }

  @Nullable
  public Set<String> getIdentitySource() {
    return (Set<String>) this.properties.get("identitySource");
  }

  @Nullable
  public String getIdentityValidationExpression() {
    return (String) this.properties.get("identityValidationExpression");
  }

  @Nullable
  public String getAuthorizerResultTtlInSeconds() {
    return (String) this.properties.get("authorizerResultTtlInSeconds");
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  @Override
  public LinkedHashMap<String, Object> getExtensionValue() {
    final LinkedHashMap<String, Object> extension = new LinkedHashMap<>();
    properties.entrySet()
        .stream()
        .filter(entry -> Objects.nonNull(entry.getValue()))
        .forEach(entry -> extension.put(entry.getKey(), entry.getValue()));
    return extension;
  }

  @Override
  public boolean isValid() {
    return !properties.isEmpty();
  }


}
