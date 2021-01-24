package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public enum HttpMethod implements EnumKey {
  POST("POST"),
  GET("GET"),
  PUT("PUT"),
  PATCH("PATCH"),
  DELETE("DELETE"),
  HEAD("HEAD"),
  OPTIONS("OPTIONS"),
  RESOLVE_FROM_METHOD(null);

  private final String key;

  HttpMethod(String key) {
    this.key = key;
  }

  public static HttpMethod from(String httpMethod) {
    return HttpMethod.valueOf(httpMethod.toUpperCase());
  }

  public String key() {
    return key;
  }

}
