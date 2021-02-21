package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.security;

import springfox.documentation.service.ApiKey;

import java.util.List;

public class CognitoAuthorizerSecurityScheme extends ApiKey {

  public static final String DEFAULT_NAME = "CognitoAuthorizer";

  /**
   * Creates Instance with default reference name 'CognitoAuthorizer'
   *
   * @param keyname name of the key
   * @param passAs  location of the key
   */
  public CognitoAuthorizerSecurityScheme(String keyname, String passAs) {
    this(DEFAULT_NAME, keyname, passAs);
  }

  public CognitoAuthorizerSecurityScheme(String name, String keyname, String passAs) {
    super(name, keyname, passAs);
  }

  public CognitoAuthorizerSecurityScheme(String name, String keyname, String passAs, ApiGatewayAuthTypeExtension authType, ApiGatewayAuthorizerExtension authorizer) {
    super(name, keyname, passAs);
    this.extension(authType);
    this.extension(authorizer);
  }

  public CognitoAuthorizerSecurityScheme authType(ApiGatewayAuthTypeExtension authType) {
    this.extension(authType);
    return this;
  }

  public CognitoAuthorizerSecurityScheme authorizer(ApiGatewayAuthorizerExtension authorizer) {
    this.extension(authorizer);
    return this;
  }

  public CognitoAuthorizerSecurityScheme extension(ApiGatewaySecurityExtension<?> extension) {
    this.addValidVendorExtensions(List.of(extension.toVendorExtension()));
    return this;
  }


}
