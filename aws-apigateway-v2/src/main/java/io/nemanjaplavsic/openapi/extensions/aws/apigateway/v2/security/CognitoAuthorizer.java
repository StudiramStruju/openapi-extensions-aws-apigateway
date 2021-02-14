package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.security;

import springfox.documentation.service.ApiKey;
import springfox.documentation.service.StringVendorExtension;

import java.util.List;
import java.util.Set;

public class CognitoAuthorizer extends ApiKey {

  private final Set<String> providerArns;

  public CognitoAuthorizer(String schemaName, String keyName, String passAs, Set<String> providerArns) {
    super(schemaName, keyName, passAs);
    this.providerArns = providerArns;
    this.addValidVendorExtensions(List.of(
        new SecurityApiGatewayAuthorizerExtension("cognito_user_pools", providerArns).toVendorExtension(),
        new StringVendorExtension("x-amazon-apigateway-authtype", "cognito_user_pools")
    ));
  }

  /**
   * Default name of the security reference is 'CognitoAuthorizer'
   * @param keyName if passing as 'header' then this is a name of the header
   * @param passAs where to
   * @param providerArns set of cognito user pool providers
   */
  public CognitoAuthorizer(String keyName, String passAs, Set<String> providerArns) {
    this("CognitoAuthorizer", keyName, passAs, providerArns);
  }

  public Set<String> getProviderArns() {
    return providerArns;
  }
}
