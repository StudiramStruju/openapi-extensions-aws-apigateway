package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationCacheKeyParametersExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationCacheNamespaceExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationConnectionIdExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationConnectionTypeExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationContentHandlingExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationCredentialsExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationHttpMethodExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationIntegrationTypeExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationPassThroughBehaviorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationRequestParametersExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationRequestTemplatesExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationResponsesExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationTimeoutInMillisExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.IntegrationUriExtension;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.VendorExtension;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ApiGatewayIntegrationExtension implements ApiGatewayExtension<ObjectVendorExtension> {

  public static final String NAME = "x-amazon-apigateway-integration";

  private IntegrationCacheKeyParametersExtension cacheKeyParameters;
  private IntegrationCacheNamespaceExtension cacheNamespace;
  private IntegrationConnectionIdExtension connectionId;
  private IntegrationConnectionTypeExtension connectionType;
  private IntegrationCredentialsExtension credentials;
  private IntegrationContentHandlingExtension contentHandling;
  private IntegrationHttpMethodExtension httpMethod;
  private IntegrationPassThroughBehaviorExtension passthroughBehavior;
  private IntegrationRequestParametersExtension requestParameters;
  private IntegrationRequestTemplatesExtension requestTemplates;
  private IntegrationResponsesExtension responses;
  private IntegrationTimeoutInMillisExtension timeoutInMillis;
  private IntegrationIntegrationTypeExtension type;
  private IntegrationUriExtension uri;
  // todo :: TlsConfig

  public ApiGatewayIntegrationExtension(IntegrationCacheKeyParametersExtension cacheKeyParameters,
                                        IntegrationCacheNamespaceExtension cacheNamespace,
                                        IntegrationConnectionIdExtension connectionId,
                                        IntegrationConnectionTypeExtension connectionType,
                                        IntegrationCredentialsExtension credentials,
                                        IntegrationContentHandlingExtension contentHandling,
                                        IntegrationHttpMethodExtension httpMethod,
                                        IntegrationPassThroughBehaviorExtension passthroughBehavior,
                                        IntegrationRequestParametersExtension requestParameters,
                                        IntegrationRequestTemplatesExtension requestTemplates,
                                        IntegrationResponsesExtension responses,
                                        IntegrationTimeoutInMillisExtension timeoutInMillis,
                                        IntegrationIntegrationTypeExtension type,
                                        IntegrationUriExtension uri) {
    this.cacheKeyParameters = Objects.requireNonNullElse(cacheKeyParameters, new IntegrationCacheKeyParametersExtension());
    this.cacheNamespace = Objects.requireNonNullElse(cacheNamespace, new IntegrationCacheNamespaceExtension());
    this.connectionId = Objects.requireNonNullElse(connectionId, new IntegrationConnectionIdExtension());
    this.connectionType = Objects.requireNonNullElse(connectionType, new IntegrationConnectionTypeExtension());
    this.credentials = Objects.requireNonNullElse(credentials, new IntegrationCredentialsExtension());
    this.contentHandling = Objects.requireNonNullElse(contentHandling, new IntegrationContentHandlingExtension());
    this.httpMethod = Objects.requireNonNullElse(httpMethod, new IntegrationHttpMethodExtension());
    this.passthroughBehavior = Objects.requireNonNullElse(passthroughBehavior, new IntegrationPassThroughBehaviorExtension());
    this.requestParameters = Objects.requireNonNullElse(requestParameters, new IntegrationRequestParametersExtension());
    this.requestTemplates = Objects.requireNonNullElse(requestTemplates, new IntegrationRequestTemplatesExtension());
    this.responses = Objects.requireNonNullElse(responses, new IntegrationResponsesExtension());
    this.timeoutInMillis = Objects.requireNonNullElse(timeoutInMillis, new IntegrationTimeoutInMillisExtension());
    this.type = Objects.requireNonNullElse(type, new IntegrationIntegrationTypeExtension());
    this.uri = Objects.requireNonNullElse(uri, new IntegrationUriExtension());
  }

  public static Builder builder() {
    return new Builder();
  }

  public ApiGatewayIntegrationExtension cacheKeyParameters(IntegrationCacheKeyParametersExtension cacheKeyParameters) {
    this.cacheKeyParameters = cacheKeyParameters;
    return this;
  }

  public ApiGatewayIntegrationExtension cacheNamespace(IntegrationCacheNamespaceExtension cacheNamespace) {
    this.cacheNamespace = cacheNamespace;
    return this;
  }

  public ApiGatewayIntegrationExtension connectionId(IntegrationConnectionIdExtension connectionId) {
    this.connectionId = connectionId;
    return this;
  }

  public ApiGatewayIntegrationExtension connectionType(IntegrationConnectionTypeExtension connectionType) {
    this.connectionType = connectionType;
    return this;
  }

  public ApiGatewayIntegrationExtension credentials(IntegrationCredentialsExtension credentials) {
    this.credentials = credentials;
    return this;
  }

  public ApiGatewayIntegrationExtension contentHandling(IntegrationContentHandlingExtension contentHandling) {
    this.contentHandling = contentHandling;
    return this;
  }

  public ApiGatewayIntegrationExtension httpMethod(IntegrationHttpMethodExtension httpMethod) {
    this.httpMethod = httpMethod;
    return this;
  }

  public ApiGatewayIntegrationExtension passthroughBehavior(IntegrationPassThroughBehaviorExtension passthroughBehavior) {
    this.passthroughBehavior = passthroughBehavior;
    return this;
  }

  public ApiGatewayIntegrationExtension requestParameters(IntegrationRequestParametersExtension requestParameters) {
    this.requestParameters = requestParameters;
    return this;
  }

  public ApiGatewayIntegrationExtension requestTemplates(IntegrationRequestTemplatesExtension requestTemplates) {
    this.requestTemplates = requestTemplates;
    return this;
  }

  public ApiGatewayIntegrationExtension responses(IntegrationResponsesExtension responses) {
    this.responses = responses;
    return this;
  }

  public ApiGatewayIntegrationExtension timeoutInMillis(IntegrationTimeoutInMillisExtension timeoutInMillis) {
    this.timeoutInMillis = timeoutInMillis;
    return this;
  }

  public ApiGatewayIntegrationExtension type(IntegrationIntegrationTypeExtension type) {
    this.type = type;
    return this;
  }

  public ApiGatewayIntegrationExtension uri(IntegrationUriExtension uri) {
    this.uri = uri;
    return this;
  }

  public IntegrationCacheKeyParametersExtension cacheKeyParameters() {
    return cacheKeyParameters;
  }

  public IntegrationCacheNamespaceExtension cacheNamespace() {
    return cacheNamespace;
  }

  public IntegrationConnectionIdExtension connectionId() {
    return connectionId;
  }

  public IntegrationConnectionTypeExtension connectionType() {
    return connectionType;
  }

  public IntegrationCredentialsExtension credentials() {
    return credentials;
  }

  public IntegrationContentHandlingExtension contentHandling() {
    return contentHandling;
  }

  public IntegrationHttpMethodExtension httpMethod() {
    return httpMethod;
  }

  public IntegrationPassThroughBehaviorExtension passthroughBehavior() {
    return passthroughBehavior;
  }

  public IntegrationRequestParametersExtension requestParameters() {
    return requestParameters;
  }

  public IntegrationRequestTemplatesExtension requestTemplates() {
    return requestTemplates;
  }

  public IntegrationResponsesExtension responses() {
    return responses;
  }

  public IntegrationTimeoutInMillisExtension timeoutInMillis() {
    return timeoutInMillis;
  }

  public IntegrationIntegrationTypeExtension type() {
    return type;
  }

  public IntegrationUriExtension uri() {
    return uri;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof ApiGatewayIntegrationExtension)) return false;
    ApiGatewayIntegrationExtension that = (ApiGatewayIntegrationExtension) object;
    return cacheKeyParameters.equals(that.cacheKeyParameters) &&
        cacheNamespace.equals(that.cacheNamespace) &&
        connectionId.equals(that.connectionId) &&
        connectionType.equals(that.connectionType) &&
        credentials.equals(that.credentials) &&
        contentHandling.equals(that.contentHandling) &&
        httpMethod.equals(that.httpMethod) &&
        passthroughBehavior.equals(that.passthroughBehavior) &&
        requestParameters.equals(that.requestParameters) &&
        requestTemplates.equals(that.requestTemplates) &&
        responses.equals(that.responses) &&
        timeoutInMillis.equals(that.timeoutInMillis) &&
        type.equals(that.type) &&
        uri.equals(that.uri);
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

  @Override
  public int hashCode() {
    return Objects.hash(cacheKeyParameters, cacheNamespace, connectionId, connectionType, credentials, contentHandling, httpMethod, passthroughBehavior, requestParameters, requestTemplates, responses, timeoutInMillis, type, uri);
  }

  public List<IntegrationExtension> getAllProperties() {
    return List.of(
        cacheKeyParameters,
        cacheNamespace,
        connectionId,
        connectionType,
        credentials,
        contentHandling,
        httpMethod,
        passthroughBehavior,
        requestParameters,
        requestTemplates,
        responses,
        timeoutInMillis,
        type,
        uri
    );
  }

  @Override
  public ObjectVendorExtension toVendorExtension() {
    final ObjectVendorExtension extension = new ObjectVendorExtension(NAME);
    getAllProperties()
        .stream()
        .filter(IntegrationExtension<VendorExtension<?>>::isValid)
        .map(IntegrationExtension<VendorExtension<?>>::toVendorExtension)
        .forEach(extension::addProperty);
    return extension;
  }

  public static class Builder {
    private IntegrationCacheKeyParametersExtension cacheKeyParameters;
    private IntegrationCacheNamespaceExtension cacheNamespace;
    private IntegrationConnectionIdExtension connectionId;
    private IntegrationConnectionTypeExtension connectionType;
    private IntegrationCredentialsExtension credentials;
    private IntegrationContentHandlingExtension contentHandling;
    private IntegrationHttpMethodExtension httpMethod;
    private IntegrationPassThroughBehaviorExtension passthroughBehavior;
    private IntegrationRequestParametersExtension requestParameters;
    private IntegrationRequestTemplatesExtension requestTemplates;
    private IntegrationResponsesExtension responses;
    private IntegrationTimeoutInMillisExtension timeoutInMillis;
    private IntegrationIntegrationTypeExtension type;
    private IntegrationUriExtension uri;

    Builder() {
    }

    public Builder cacheKeyParameters(IntegrationCacheKeyParametersExtension cacheKeyParameters) {
      this.cacheKeyParameters = cacheKeyParameters;
      return this;
    }

    public Builder cacheNamespace(IntegrationCacheNamespaceExtension cacheNamespace) {
      this.cacheNamespace = cacheNamespace;
      return this;
    }

    public Builder connectionId(IntegrationConnectionIdExtension connectionId) {
      this.connectionId = connectionId;
      return this;
    }

    public Builder connectionType(IntegrationConnectionTypeExtension connectionType) {
      this.connectionType = connectionType;
      return this;
    }

    public Builder credentials(IntegrationCredentialsExtension credentials) {
      this.credentials = credentials;
      return this;
    }

    public Builder contentHandling(IntegrationContentHandlingExtension contentHandling) {
      this.contentHandling = contentHandling;
      return this;
    }

    public Builder httpMethod(IntegrationHttpMethodExtension httpMethod) {
      this.httpMethod = httpMethod;
      return this;
    }

    public Builder passthroughBehavior(IntegrationPassThroughBehaviorExtension passthroughBehavior) {
      this.passthroughBehavior = passthroughBehavior;
      return this;
    }

    public Builder requestParameters(IntegrationRequestParametersExtension requestParameters) {
      this.requestParameters = requestParameters;
      return this;
    }

    public Builder requestTemplates(IntegrationRequestTemplatesExtension requestTemplates) {
      this.requestTemplates = requestTemplates;
      return this;
    }

    public Builder responses(IntegrationResponsesExtension responses) {
      this.responses = responses;
      return this;
    }

    public Builder timeoutInMillis(IntegrationTimeoutInMillisExtension timeoutInMillis) {
      this.timeoutInMillis = timeoutInMillis;
      return this;
    }

    public Builder type(IntegrationIntegrationTypeExtension type) {
      this.type = type;
      return this;
    }

    public Builder uri(IntegrationUriExtension uri) {
      this.uri = uri;
      return this;
    }

    public ApiGatewayIntegrationExtension build() {
      return new ApiGatewayIntegrationExtension(cacheKeyParameters, cacheNamespace, connectionId, connectionType, credentials, contentHandling, httpMethod, passthroughBehavior, requestParameters, requestTemplates, responses, timeoutInMillis, type, uri);
    }
  }
}
