package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ConnectionType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.PassThroughBehavior;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Set;

@Getter
@EqualsAndHashCode
@ToString
@ConfigurationProperties(prefix = "openapi.extension.aws.api-gateway.service")
public class ApiGatewayServiceProperties {
  @Nullable
  private final String uri;
  @Nullable
  private final String basePath;
  @Nullable
  private final String connectionId;
  @Nullable
  private final ConnectionType connectionType;
  @Nullable
  private final IntegrationType integrationType;
  @Nullable
  private final Set<String> cacheKeyParameters;
  @Nullable
  private final String cacheNamespace;
  @Nullable
  private final String credentials;
  @Nullable
  private final ContentHandling contentHandling;
  @Nullable
  private final PassThroughBehavior passthroughBehavior;
  @Nullable
  private final Integer timeoutInMillis;

  @ConstructorBinding
  public ApiGatewayServiceProperties(@Nullable String uri,
                                     @Nullable String basePath,
                                     @Nullable String connectionId,
                                     @Nullable ConnectionType connectionType,
                                     @Nullable IntegrationType integrationType,
                                     @Nullable Set<String> cacheKeyParameters,
                                     @Nullable String cacheNamespace,
                                     @Nullable String credentials,
                                     @Nullable ContentHandling contentHandling,
                                     @Nullable PassThroughBehavior passthroughBehavior,
                                     @Nullable Integer timeoutInMillis) {
    this.uri = uri;
    this.basePath = basePath;
    this.connectionId = connectionId;
    this.connectionType = Objects.requireNonNullElse(connectionType, ConnectionType.NONE);
    this.integrationType = integrationType;
    this.cacheKeyParameters = cacheKeyParameters;
    this.cacheNamespace = cacheNamespace;
    this.credentials = credentials;
    this.contentHandling = Objects.requireNonNullElse(contentHandling, ContentHandling.NONE);
    this.passthroughBehavior = Objects.requireNonNullElse(passthroughBehavior, PassThroughBehavior.NONE);
    this.timeoutInMillis = timeoutInMillis;
  }
}
