package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.PassThroughBehavior;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationPassThroughBehaviorExtension implements ApiGatewayIntegrationExtension<String> {

  public static final String NAME = "passthroughBehavior";

  private final PassThroughBehavior passThroughBehavior;

  public IntegrationPassThroughBehaviorExtension() {
    this(null);
  }

  public IntegrationPassThroughBehaviorExtension(@Nullable PassThroughBehavior passThroughBehavior) {
    this.passThroughBehavior = Objects.requireNonNullElse(passThroughBehavior, PassThroughBehavior.NONE);
  }

  public PassThroughBehavior getPassThroughBehavior() {
    return passThroughBehavior;
  }

  @Override
  public String getExtensionKey() {
    return NAME;
  }

  public String getExtensionValue() {
    return passThroughBehavior.key();
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(passThroughBehavior);
      return Objects.nonNull(passThroughBehavior.key());
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationPassThroughBehaviorExtension)) return false;
    IntegrationPassThroughBehaviorExtension that = (IntegrationPassThroughBehaviorExtension) object;
    return passThroughBehavior == that.passThroughBehavior;
  }

  @Override
  public int hashCode() {
    return Objects.hash(passThroughBehavior);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationPassThroughBehaviorExtension.class.getSimpleName() + "[", "]")
        .add("passThroughBehavior=" + passThroughBehavior)
        .toString();
  }
}
