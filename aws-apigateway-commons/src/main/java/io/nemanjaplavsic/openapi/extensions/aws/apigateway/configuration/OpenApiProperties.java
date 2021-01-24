package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ConnectionType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.PassThroughBehavior;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@EqualsAndHashCode
@ToString
@ConfigurationProperties(prefix = "openapi")
public class OpenApiProperties {

  private final boolean enabled;
  private final OpenApiInfoProperties apiInfo;
  private final OpenApiExtensionProperties extension;


  @ConstructorBinding
  public OpenApiProperties(boolean enabled, OpenApiInfoProperties apiInfo, OpenApiExtensionProperties extension) {
    this.enabled = enabled;
    this.apiInfo = apiInfo;
    this.extension = extension;
  }

  @Getter
  @EqualsAndHashCode
  @ToString
  public static class OpenApiInfoProperties {
    private final String host;
    private final String title;
    private final String version;

    @ConstructorBinding
    public OpenApiInfoProperties(String host, String title, String version) {
      this.host = host;
      this.title = title;
      this.version = version;
    }
  }

  @Getter
  @EqualsAndHashCode
  @ToString
  public static class OpenApiExtensionProperties {

    private final OpenApiExtensionAwsProperties aws;

    @ConstructorBinding
    public OpenApiExtensionProperties(OpenApiExtensionAwsProperties aws) {
      this.aws = aws;
    }

    @Getter
    @EqualsAndHashCode
    @ToString
    public static class OpenApiExtensionAwsProperties {

      private final OpenApiExtensionAwsApiGatewayProperties apiGateway;

      @ConstructorBinding
      public OpenApiExtensionAwsProperties(OpenApiExtensionAwsApiGatewayProperties apiGateway) {
        this.apiGateway = apiGateway;
      }

      @Getter
      @EqualsAndHashCode
      @ToString
      public static class OpenApiExtensionAwsApiGatewayProperties {

        private final ApiGatewayServiceProperties service;

        @ConstructorBinding
        public OpenApiExtensionAwsApiGatewayProperties(ApiGatewayServiceProperties service) {
          this.service = service;
        }

        @Getter
        @EqualsAndHashCode
        @ToString
        public static class ApiGatewayServiceProperties {
          private final String uri;
          private final String basePath;
          private final String connectionId;
          private final ConnectionType connectionType;
          private final String[] cacheKeyParameters;
          private final String cacheNamespace;
          private final String credentials;
          private final ContentHandling contentHandling;
          private final PassThroughBehavior passthroughBehavior;

          @ConstructorBinding
          public ApiGatewayServiceProperties(String uri,
                                             String basePath,
                                             String connectionId,
                                             ConnectionType connectionType,
                                             String[] cacheKeyParameters,
                                             String cacheNamespace,
                                             String credentials,
                                             ContentHandling contentHandling,
                                             PassThroughBehavior passthroughBehavior) {
            this.uri = uri;
            this.basePath = basePath;
            this.connectionId = connectionId;
            this.connectionType = connectionType;
            this.cacheKeyParameters = cacheKeyParameters;
            this.cacheNamespace = cacheNamespace;
            this.credentials = credentials;
            this.contentHandling = contentHandling;
            this.passthroughBehavior = passthroughBehavior;
          }
        }
      }
    }

  }
}
