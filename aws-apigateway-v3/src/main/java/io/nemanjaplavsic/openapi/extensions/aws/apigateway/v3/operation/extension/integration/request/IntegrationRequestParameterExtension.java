package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.MethodRequestParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.RequestParameterSource;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ConvertableExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.extension.ValidatableExtension;
import org.slf4j.Logger;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationRequestParameterExtension implements ConvertableExtension<String>, ValidatableExtension {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(IntegrationRequestParameterExtension.class);

  private final RequestParameterSource source;
  private final IntegrationRequestParameterType integrationParameterType;
  private final String integrationParameterName;
  private MethodRequestParameterType methodParameterType;
  @Nullable
  private String methodParameterName;
  @Nullable
  private String contextVariableName;
  @Nullable
  private String stageVariableName;
  @Nullable
  private String staticValueName;

  public IntegrationRequestParameterExtension(RequestParameterSource source,
                                              IntegrationRequestParameterType integrationParameterType,
                                              String integrationParameterName) {
    this(source, integrationParameterType, integrationParameterName, MethodRequestParameterType.matching(integrationParameterType), integrationParameterName, null, null, null);
  }

  public IntegrationRequestParameterExtension(RequestParameterSource source,
                                              IntegrationRequestParameterType integrationParameterType,
                                              String integrationParameterName,
                                              @Nullable MethodRequestParameterType methodParameterType,
                                              @Nullable String methodParameterName,
                                              @Nullable String contextVariableName,
                                              @Nullable String stageVariableName,
                                              @Nullable String staticValueName) {
    this.source = source;
    this.integrationParameterType = integrationParameterType;
    this.integrationParameterName = integrationParameterName;
    this.methodParameterType = Objects.requireNonNullElse(methodParameterType, MethodRequestParameterType.NONE);
    this.methodParameterName = methodParameterName;
    this.contextVariableName = contextVariableName;
    this.stageVariableName = stageVariableName;
    this.staticValueName = staticValueName;
  }

  public IntegrationRequestParameterExtension methodParameterType(MethodRequestParameterType methodParameterType) {
    this.methodParameterType = methodParameterType;
    return this;
  }

  public IntegrationRequestParameterExtension methodParameterName(@Nullable String methodParameterName) {
    this.methodParameterName = methodParameterName;
    return this;
  }

  public IntegrationRequestParameterExtension contextVariableName(@Nullable String contextVariableName) {
    this.contextVariableName = contextVariableName;
    return this;
  }

  public IntegrationRequestParameterExtension stageVariableName(@Nullable String stageVariableName) {
    this.stageVariableName = stageVariableName;
    return this;
  }

  public IntegrationRequestParameterExtension staticValueName(@Nullable String staticValueName) {
    this.staticValueName = staticValueName;
    return this;
  }

  public RequestParameterSource getSource() {
    return source;
  }

  public IntegrationRequestParameterType getIntegrationParameterType() {
    return integrationParameterType;
  }

  public String getIntegrationParameterName() {
    return integrationParameterName;
  }

  public MethodRequestParameterType getMethodParameterType() {
    return methodParameterType;
  }

  @Nullable
  public String getMethodParameterName() {
    return methodParameterName;
  }

  @Nullable
  public String getContextVariableName() {
    return contextVariableName;
  }

  @Nullable
  public String getStageVariableName() {
    return stageVariableName;
  }

  @Nullable
  public String getStaticValueName() {
    return staticValueName;
  }

  public boolean matches(IntegrationRequestParameterExtension parameterExtension) {
    return integrationParameterType.equals(parameterExtension.getIntegrationParameterType())
        && integrationParameterName.equals(parameterExtension.getIntegrationParameterName());
  }

  public String getExtensionKey() {
    return String.format("integration.request.%s.%s", integrationParameterType.key(), integrationParameterName);
  }

  public String getExtensionValue() {
    switch (source) {
      case METHOD:
        return String.format("method.request.%s.%s", methodParameterType.key(), methodParameterName);
      case CONTEXT:
        return String.format("context.%s", contextVariableName);
      case STAGE:
        return String.format("stageVariables.%s", stageVariableName);
      case STATIC:
        return String.format("%s", staticValueName);
      default:
        throw new RuntimeException("IntegrationRequestParameter 'source' must be defined!");
    }
  }

  public boolean isValid() {
    try {
      Objects.requireNonNull(source);
      switch (source) {
        case METHOD:
          Objects.requireNonNull(methodParameterType.key(),
              String.format("IntegrationRequestParameter requires methodParameterType for source = '%s'", source)
          );
          Objects.requireNonNull(methodParameterName,
              String.format("IntegrationRequestParameter requires methodParameterName for source = '%s'", source)
          );
          return true;
        case CONTEXT:
          Objects.requireNonNull(contextVariableName,
              String.format("IntegrationRequestParameter requires contextVariableName for source = '%s'", source)
          );
          return true;
        case STAGE:
          Objects.requireNonNull(stageVariableName,
              String.format("IntegrationRequestParameter requires stageVariableName for source = '%s'", source)
          );
          return true;
        case STATIC:
          Objects.requireNonNull(staticValueName,
              String.format("IntegrationRequestParameter requires staticValueName for source = '%s'", source)
          );
          return true;
        default:
          return false;
      }
    } catch (NullPointerException e) {
      log.warn(e.getMessage(), e);
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationRequestParameterExtension)) return false;
    IntegrationRequestParameterExtension that = (IntegrationRequestParameterExtension) object;
    return source == that.source &&
        integrationParameterType == that.integrationParameterType &&
        integrationParameterName.equals(that.integrationParameterName) &&
        methodParameterType == that.methodParameterType &&
        Objects.equals(methodParameterName, that.methodParameterName) &&
        Objects.equals(contextVariableName, that.contextVariableName) &&
        Objects.equals(stageVariableName, that.stageVariableName) &&
        Objects.equals(staticValueName, that.staticValueName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, integrationParameterType, integrationParameterName, methodParameterType, methodParameterName, contextVariableName, stageVariableName, staticValueName);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationRequestParameterExtension.class.getSimpleName() + "[", "]")
        .add("source=" + source)
        .add("integrationParameterType=" + integrationParameterType)
        .add("integrationParameterName='" + integrationParameterName + "'")
        .add("methodParameterType=" + methodParameterType)
        .add("methodParameterName='" + methodParameterName + "'")
        .add("contextVariableName='" + contextVariableName + "'")
        .add("stageVariableName='" + stageVariableName + "'")
        .add("staticValueName='" + staticValueName + "'")
        .toString();
  }
}
