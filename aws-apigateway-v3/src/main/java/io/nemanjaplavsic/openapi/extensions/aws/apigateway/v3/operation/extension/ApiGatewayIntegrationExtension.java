package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ConnectionType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.ContentHandling;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.OperationApiGatewayExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationCacheKeyParametersExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationCacheNamespaceExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationConnectionIdExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationConnectionTypeExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationContentHandlingExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationCredentialsExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationHttpMethodExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationIntegrationTypeExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationPassThroughBehaviorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationRequestParametersExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationRequestTemplatesExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationResponsesExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationTimeoutInMillisExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationUriExtension;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public class ApiGatewayIntegrationExtension implements OperationApiGatewayExtension<LinkedHashMap<String, Object>> {

  public static final String NAME = "x-amazon-apigateway-integration";

  private IntegrationRequestParametersExtension requestParameters;

  private IntegrationRequestTemplatesExtension requestTemplates;

  private IntegrationResponsesExtension responses;

  @Nullable
  private IntegrationCacheKeyParametersExtension cacheKeyParameters;
  @Nullable
  private IntegrationCacheNamespaceExtension cacheNamespace;
  @Nullable
  private IntegrationConnectionIdExtension connectionId;
  @Nullable
  private IntegrationConnectionTypeExtension connectionType;
  @Nullable
  private IntegrationCredentialsExtension credentials;
  @Nullable
  private IntegrationContentHandlingExtension contentHandling;
  @Nullable
  private IntegrationHttpMethodExtension httpMethod;
  @Nullable
  private IntegrationPassThroughBehaviorExtension passthroughBehavior;
  @Nullable
  private IntegrationTimeoutInMillisExtension timeoutInMillis;
  @Nullable
  private IntegrationIntegrationTypeExtension type;
  @Nullable
  private IntegrationUriExtension uri;

  public ApiGatewayIntegrationExtension() {
    this.cacheKeyParameters = null;
    this.cacheNamespace = null;
    this.connectionId = null;
    this.connectionType = null;
    this.credentials = null;
    this.contentHandling = null;
    this.httpMethod = null;
    this.passthroughBehavior = null;
    this.requestParameters = new IntegrationRequestParametersExtension();
    this.requestTemplates = new IntegrationRequestTemplatesExtension();
    this.responses = new IntegrationResponsesExtension();
    this.timeoutInMillis = null;
    this.type = null;
    this.uri = null;
  }

  public ApiGatewayIntegrationExtension cacheKeyParameters(@Nullable Set<String> cacheKeyParameters) {
    this.cacheKeyParameters = new IntegrationCacheKeyParametersExtension(cacheKeyParameters);
    return this;
  }

  public ApiGatewayIntegrationExtension cacheKeyParameters(@Nullable IntegrationCacheKeyParametersExtension cacheKeyParameters) {
    this.cacheKeyParameters = cacheKeyParameters;
    return this;
  }

  public ApiGatewayIntegrationExtension cacheNamespace(@Nullable String cacheNamespace) {
    this.cacheNamespace = new IntegrationCacheNamespaceExtension(cacheNamespace);
    return this;
  }

  public ApiGatewayIntegrationExtension cacheNamespace(@Nullable IntegrationCacheNamespaceExtension cacheNamespace) {
    this.cacheNamespace = cacheNamespace;
    return this;
  }

  public ApiGatewayIntegrationExtension connectionId(@Nullable String connectionId) {
    this.connectionId = new IntegrationConnectionIdExtension(connectionId);
    return this;
  }

  public ApiGatewayIntegrationExtension connectionId(@Nullable IntegrationConnectionIdExtension connectionId) {
    this.connectionId = connectionId;
    return this;
  }

  public ApiGatewayIntegrationExtension connectionType(@Nullable ConnectionType connectionType) {
    this.connectionType = new IntegrationConnectionTypeExtension(connectionType);
    return this;
  }

  public ApiGatewayIntegrationExtension connectionType(@Nullable IntegrationConnectionTypeExtension connectionType) {
    this.connectionType = connectionType;
    return this;
  }

  public ApiGatewayIntegrationExtension credentials(@Nullable String credentials) {
    this.credentials = new IntegrationCredentialsExtension(credentials);
    return this;
  }

  public ApiGatewayIntegrationExtension credentials(@Nullable IntegrationCredentialsExtension credentials) {
    this.credentials = credentials;
    return this;
  }

  public ApiGatewayIntegrationExtension contentHandling(@Nullable ContentHandling contentHandling) {
    this.contentHandling = new IntegrationContentHandlingExtension(contentHandling);
    return this;
  }

  public ApiGatewayIntegrationExtension contentHandling(@Nullable IntegrationContentHandlingExtension contentHandling) {
    this.contentHandling = contentHandling;
    return this;
  }

  public ApiGatewayIntegrationExtension httpMethod(RequestMethod requestMethod) {
    this.httpMethod = new IntegrationHttpMethodExtension(requestMethod);
    return this;
  }

  public ApiGatewayIntegrationExtension httpMethod(@Nullable IntegrationHttpMethodExtension httpMethod) {
    this.httpMethod = httpMethod;
    return this;
  }

  public ApiGatewayIntegrationExtension passthroughBehavior(@Nullable IntegrationPassThroughBehaviorExtension passthroughBehavior) {
    this.passthroughBehavior = passthroughBehavior;
    return this;
  }

  public ApiGatewayIntegrationExtension requestParameters(@Nullable IntegrationRequestParametersExtension requestParameters) {
    this.requestParameters = Objects.requireNonNullElse(requestParameters, new IntegrationRequestParametersExtension());
    return this;
  }

  public ApiGatewayIntegrationExtension requestTemplates(@Nullable IntegrationRequestTemplatesExtension requestTemplates) {
    this.requestTemplates = Objects.requireNonNullElse(requestTemplates, new IntegrationRequestTemplatesExtension());
    return this;
  }

  public ApiGatewayIntegrationExtension responses(@Nullable IntegrationResponsesExtension responses) {
    this.responses = Objects.requireNonNullElse(responses, new IntegrationResponsesExtension());
    return this;
  }

  public ApiGatewayIntegrationExtension timeoutInMillis(@Nullable IntegrationTimeoutInMillisExtension timeoutInMillis) {
    this.timeoutInMillis = timeoutInMillis;
    return this;
  }

  public ApiGatewayIntegrationExtension type(@Nullable IntegrationIntegrationTypeExtension type) {
    this.type = type;
    return this;
  }

  public ApiGatewayIntegrationExtension uri(@Nullable IntegrationUriExtension uri) {
    this.uri = uri;
    return this;
  }

  public IntegrationRequestParametersExtension getRequestParameters() {
    return requestParameters;
  }

  public IntegrationRequestTemplatesExtension getRequestTemplates() {
    return requestTemplates;
  }

  public IntegrationResponsesExtension getResponses() {
    return responses;
  }

  @Nullable
  public IntegrationCacheKeyParametersExtension getCacheKeyParameters() {
    return cacheKeyParameters;
  }

  @Nullable
  public IntegrationCacheNamespaceExtension getCacheNamespace() {
    return cacheNamespace;
  }

  @Nullable
  public IntegrationConnectionIdExtension getConnectionId() {
    return connectionId;
  }

  @Nullable
  public IntegrationConnectionTypeExtension getConnectionType() {
    return connectionType;
  }

  @Nullable
  public IntegrationCredentialsExtension getCredentials() {
    return credentials;
  }

  @Nullable
  public IntegrationContentHandlingExtension getContentHandling() {
    return contentHandling;
  }

  @Nullable
  public IntegrationHttpMethodExtension getHttpMethod() {
    return httpMethod;
  }

  @Nullable
  public IntegrationPassThroughBehaviorExtension getPassthroughBehavior() {
    return passthroughBehavior;
  }

  @Nullable
  public IntegrationTimeoutInMillisExtension getTimeoutInMillis() {
    return timeoutInMillis;
  }

  @Nullable
  public IntegrationIntegrationTypeExtension getType() {
    return type;
  }

  @Nullable
  public IntegrationUriExtension getUri() {
    return uri;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof ApiGatewayIntegrationExtension)) return false;
    ApiGatewayIntegrationExtension that = (ApiGatewayIntegrationExtension) object;
    return Objects.equals(cacheKeyParameters, that.cacheKeyParameters) &&
        Objects.equals(cacheNamespace, that.cacheNamespace) &&
        Objects.equals(connectionId, that.connectionId) &&
        Objects.equals(connectionType, that.connectionType) &&
        Objects.equals(credentials, that.credentials) &&
        Objects.equals(contentHandling, that.contentHandling) &&
        Objects.equals(httpMethod, that.httpMethod) &&
        Objects.equals(passthroughBehavior, that.passthroughBehavior) &&
        requestParameters.equals(that.requestParameters) &&
        requestTemplates.equals(that.requestTemplates) &&
        responses.equals(that.responses) &&
        Objects.equals(timeoutInMillis, that.timeoutInMillis) &&
        Objects.equals(type, that.type) &&
        Objects.equals(uri, that.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cacheKeyParameters, cacheNamespace, connectionId, connectionType, credentials, contentHandling, httpMethod, passthroughBehavior, requestParameters, requestTemplates, responses, timeoutInMillis, type, uri);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ApiGatewayIntegrationExtension.class.getSimpleName() + "[", "]")
        .add("cacheKeyParameters=" + cacheKeyParameters)
        .add("cacheNamespace=" + cacheNamespace)
        .add("connectionId=" + connectionId)
        .add("connectionType=" + connectionType)
        .add("credentials=" + credentials)
        .add("contentHandling=" + contentHandling)
        .add("httpMethod=" + httpMethod)
        .add("passthroughBehavior=" + passthroughBehavior)
        .add("requestParameters=" + requestParameters)
        .add("requestTemplates=" + requestTemplates)
        .add("responses=" + responses)
        .add("timeoutInMillis=" + timeoutInMillis)
        .add("type=" + type)
        .add("uri=" + uri)
        .toString();
  }


  public List<io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.ApiGatewayIntegrationExtension<?>> getAllProperties() {
    List<io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.ApiGatewayIntegrationExtension<?>> properties = new ArrayList<>();
    if (Objects.nonNull(cacheKeyParameters)) properties.add(cacheKeyParameters);
    if (Objects.nonNull(cacheNamespace)) properties.add(cacheNamespace);
    if (Objects.nonNull(connectionId)) properties.add(connectionId);
    if (Objects.nonNull(connectionType)) properties.add(connectionType);
    if (Objects.nonNull(credentials)) properties.add(credentials);
    if (Objects.nonNull(contentHandling)) properties.add(contentHandling);
    if (Objects.nonNull(httpMethod)) properties.add(httpMethod);
    if (Objects.nonNull(passthroughBehavior)) properties.add(passthroughBehavior);
    if (Objects.nonNull(timeoutInMillis)) properties.add(timeoutInMillis);
    if (Objects.nonNull(type)) properties.add(type);
    if (Objects.nonNull(uri)) properties.add(uri);
    properties.add(requestParameters);
    properties.add(requestTemplates);
    properties.add(responses);
    return properties;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  @Override
  public LinkedHashMap<String, Object> getExtensionValue() {
    final LinkedHashMap<String, Object> extension = new LinkedHashMap<>();
    getAllProperties()
        .stream()
        .filter(io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.ApiGatewayIntegrationExtension::isValid)
        .forEach(property -> extension.put(property.getExtensionKey(), property.getExtensionValue()));
    return extension;
  }

  @Override
  public boolean isValid() {
    return true;
  }
}
