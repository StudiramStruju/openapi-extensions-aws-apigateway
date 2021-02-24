package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.gatewayresponse.response;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.gatewayresponse.GatewayResponseParameterSource;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.gatewayresponse.ResponseExtension;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

public class GatewayResponseParameterExtension implements ResponseExtension<String> {


  private final GatewayResponseParameterSource source;
  private final String headerName;
  @Nullable
  private String contextValue;
  @Nullable
  private String staticValue;
  @Nullable
  private String stageVariable;

  public GatewayResponseParameterExtension(GatewayResponseParameterSource source, String headerName) {
    this.source = source;
    this.headerName = headerName;
  }

  public GatewayResponseParameterExtension(GatewayResponseParameterSource source, String headerName, @Nullable String contextValue, @Nullable String staticValue, @Nullable String stageVariable) {
    this.source = source;
    this.headerName = headerName;
    this.contextValue = contextValue;
    this.staticValue = staticValue;
    this.stageVariable = stageVariable;
  }

  public static GatewayResponseParameterExtension CorsOrigin(String staticValue) {
    return new GatewayResponseParameterExtension(GatewayResponseParameterSource.STATIC, "Access-Control-Allow-Origin", null, staticValue, null);
  }

  public static GatewayResponseParameterExtension CorsMethods(String staticValue) {
    return new GatewayResponseParameterExtension(GatewayResponseParameterSource.STATIC, "Access-Control-Allow-Methods", null, staticValue, null);
  }

  public static GatewayResponseParameterExtension CorsHeaders(String staticValue) {
    return new GatewayResponseParameterExtension(GatewayResponseParameterSource.STATIC, "Access-Control-Allow-Headers", null, staticValue, null);
  }

  public GatewayResponseParameterExtension contextValue(@Nullable String contextValue) {
    this.contextValue = contextValue;
    return this;
  }

  public GatewayResponseParameterExtension staticValue(@Nullable String staticValue) {
    this.staticValue = staticValue;
    return this;
  }

  public GatewayResponseParameterExtension stageVariable(@Nullable String stageVariable) {
    this.stageVariable = stageVariable;
    return this;
  }

  public GatewayResponseParameterSource source() {
    return source;
  }

  public String headerName() {
    return headerName;
  }

  @Nullable
  public String contextValue() {
    return contextValue;
  }

  @Nullable
  public String staticValue() {
    return staticValue;
  }

  @Nullable
  public String stageVariable() {
    return stageVariable;
  }

  public boolean matches(GatewayResponseParameterExtension param) {
    return Objects.equals(source, param.source()) && Objects.equals(headerName, param.headerName());
  }

  @Override
  public String getExtensionKey() {
    return String.format("gatewayresponse.header.%s", headerName);
  }

  @Override
  public String getExtensionValue() {
    switch (source) {
      case CONTEXT:
        return String.format("$context.%s", contextValue);
      case STATIC:
        return String.format("%s", staticValue);
      case STAGE_VARIABLE:
        return String.format("$stageVariables.%s", stageVariable);
      default:
        return null;
    }
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(source);
      Objects.requireNonNull(headerName);
      switch (source) {
        case CONTEXT:
          Objects.requireNonNull(contextValue);
          return true;
        case STATIC:
          Objects.requireNonNull(staticValue);
          return true;
        case STAGE_VARIABLE:
          Objects.requireNonNull(stageVariable);
          return true;
        default:
          return false;
      }
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof GatewayResponseParameterExtension)) return false;
    GatewayResponseParameterExtension that = (GatewayResponseParameterExtension) object;
    return source == that.source &&
        headerName.equals(that.headerName) &&
        Objects.equals(contextValue, that.contextValue) &&
        Objects.equals(staticValue, that.staticValue) &&
        Objects.equals(stageVariable, that.stageVariable);
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, headerName, contextValue, staticValue, stageVariable);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GatewayResponseParameterExtension.class.getSimpleName() + "[", "]")
        .add("source=" + source)
        .add("headerName='" + headerName + "'")
        .add("contextValue='" + contextValue + "'")
        .add("staticValue='" + staticValue + "'")
        .add("stageVariable='" + stageVariable + "'")
        .toString();
  }
}
