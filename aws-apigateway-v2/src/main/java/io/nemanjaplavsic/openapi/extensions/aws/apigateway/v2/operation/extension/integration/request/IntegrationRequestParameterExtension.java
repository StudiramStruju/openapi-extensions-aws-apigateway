package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.request;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.IntegrationRequestParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.MethodRequestParameterType;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.RequestParameterSource;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.exception.IntegrationExtensionException;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.operation.extension.integration.IntegrationExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import springfox.documentation.service.StringVendorExtension;

import java.util.Objects;
import java.util.StringJoiner;

@Slf4j
public class IntegrationRequestParameterExtension implements IntegrationExtension<StringVendorExtension> {

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
    this(source, integrationParameterType, integrationParameterName, null, null, null, null, null);
  }

  public IntegrationRequestParameterExtension(RequestParameterSource source,
                                              IntegrationRequestParameterType integrationParameterType,
                                              String integrationParameterName,
                                              @Nullable MethodRequestParameterType methodParameterType,
                                              @Nullable String methodParameterName,
                                              @Nullable String contextVariableName,
                                              @Nullable String stageVariableName,
                                              @Nullable String staticValueName) {
    this.source = Objects.requireNonNull(source, "Cannot create instance of IntegrationRequestParameterExtension with source as null value!");
    this.integrationParameterType = Objects.requireNonNull(integrationParameterType, "Cannot create instance of IntegrationRequestParameterExtension with integrationParameterType as null value!");
    this.integrationParameterName = Objects.requireNonNull(integrationParameterName, "Cannot create instance of IntegrationRequestParameterExtension with integrationParameterName as null value!");
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

  public RequestParameterSource source() {
    return source;
  }

  public IntegrationRequestParameterType integrationParameterType() {
    return integrationParameterType;
  }

  public String integrationParameterName() {
    return integrationParameterName;
  }

  public MethodRequestParameterType methodParameterType() {
    return methodParameterType;
  }

  @Nullable
  public String methodParameterName() {
    return methodParameterName;
  }

  @Nullable
  public String contextVariableName() {
    return contextVariableName;
  }

  @Nullable
  public String stageVariableName() {
    return stageVariableName;
  }

  @Nullable
  public String staticValueName() {
    return staticValueName;
  }

  public boolean matches(IntegrationRequestParameterExtension parameterExtension) {
    return integrationParameterType.equals(parameterExtension.integrationParameterType())
        && integrationParameterName.equals(parameterExtension.integrationParameterName());
  }

  @Override
  public StringVendorExtension toVendorExtension() {
    switch (source) {
      case METHOD:
        return new StringVendorExtension(
            String.format("integration.request.%s.%s", integrationParameterType.key(), integrationParameterName),
            String.format("method.request.%s.%s", methodParameterType.key(), methodParameterName)
        );
      case CONTEXT:
        return new StringVendorExtension(
            String.format("integration.request.%s.%s", integrationParameterType.key(), integrationParameterName),
            String.format("context.%s", contextVariableName)
        );
      case STAGE:
        return new StringVendorExtension(
            String.format("integration.request.%s.%s", integrationParameterType.key(), integrationParameterName),
            String.format("stageVariables.%s", stageVariableName)
        );
      case STATIC:
        return new StringVendorExtension(
            String.format("integration.request.%s.%s", integrationParameterType.key(), integrationParameterName),
            String.format("%s", staticValueName)
        );
      default:
        throw new IntegrationExtensionException("IntegrationRequestParameter 'source' must be defined!");
    }
  }

  @Override
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
