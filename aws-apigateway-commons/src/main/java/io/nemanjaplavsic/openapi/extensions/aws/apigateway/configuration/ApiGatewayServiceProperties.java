package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ConnectionType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.PassThroughBehavior;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@ConfigurationProperties(prefix = "openapi.extension.aws.api-gateway.service")
public class ApiGatewayServiceProperties {
  @Nullable
  private final String uri;
  @Nullable
  private final String basePath;
  @Nullable
  private final String connectionId;
  private final ConnectionType connectionType;
  @Nullable
  private final IntegrationType integrationType;
  @Nullable
  private final Set<String> cacheKeyParameters;
  @Nullable
  private final String cacheNamespace;
  @Nullable
  private final String credentials;
  private final ContentHandling contentHandling;
  private final PassThroughBehavior passThroughBehavior;
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
                                     @Nullable PassThroughBehavior passThroughBehavior,
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
    this.passThroughBehavior = Objects.requireNonNullElse(passThroughBehavior, PassThroughBehavior.NONE);
    this.timeoutInMillis = timeoutInMillis;
  }

  @Nullable
  public String getUri() {
    return uri;
  }

  @Nullable
  public String getBasePath() {
    return basePath;
  }

  @Nullable
  public String getConnectionId() {
    return connectionId;
  }

  public ConnectionType getConnectionType() {
    return connectionType;
  }

  @Nullable
  public IntegrationType getIntegrationType() {
    return integrationType;
  }

  @Nullable
  public Set<String> getCacheKeyParameters() {
    return cacheKeyParameters;
  }

  @Nullable
  public String getCacheNamespace() {
    return cacheNamespace;
  }

  @Nullable
  public String getCredentials() {
    return credentials;
  }

  public ContentHandling getContentHandling() {
    return contentHandling;
  }

  public PassThroughBehavior getPassThroughBehavior() {
    return passThroughBehavior;
  }

  @Nullable
  public Integer getTimeoutInMillis() {
    return timeoutInMillis;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof ApiGatewayServiceProperties)) return false;
    ApiGatewayServiceProperties that = (ApiGatewayServiceProperties) object;
    return Objects.equals(getUri(), that.getUri()) &&
        Objects.equals(getBasePath(), that.getBasePath()) &&
        Objects.equals(getConnectionId(), that.getConnectionId()) &&
        getConnectionType() == that.getConnectionType() &&
        getIntegrationType() == that.getIntegrationType() &&
        Objects.equals(getCacheKeyParameters(), that.getCacheKeyParameters()) &&
        Objects.equals(getCacheNamespace(), that.getCacheNamespace()) &&
        Objects.equals(getCredentials(), that.getCredentials()) &&
        getContentHandling() == that.getContentHandling() &&
        getPassThroughBehavior() == that.getPassThroughBehavior() &&
        Objects.equals(getTimeoutInMillis(), that.getTimeoutInMillis());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUri(), getBasePath(), getConnectionId(), getConnectionType(), getIntegrationType(), getCacheKeyParameters(), getCacheNamespace(), getCredentials(), getContentHandling(), getPassThroughBehavior(), getTimeoutInMillis());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ApiGatewayServiceProperties.class.getSimpleName() + "[", "]")
        .add("uri='" + uri + "'")
        .add("basePath='" + basePath + "'")
        .add("connectionId='" + connectionId + "'")
        .add("connectionType=" + connectionType)
        .add("integrationType=" + integrationType)
        .add("cacheKeyParameters=" + cacheKeyParameters)
        .add("cacheNamespace='" + cacheNamespace + "'")
        .add("credentials='" + credentials + "'")
        .add("contentHandling=" + contentHandling)
        .add("passThroughBehavior=" + passThroughBehavior)
        .add("timeoutInMillis=" + timeoutInMillis)
        .toString();
  }
}
