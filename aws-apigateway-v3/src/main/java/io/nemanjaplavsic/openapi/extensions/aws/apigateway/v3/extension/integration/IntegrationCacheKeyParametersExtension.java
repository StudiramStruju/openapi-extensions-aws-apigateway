package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.integration;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public class IntegrationCacheKeyParametersExtension implements IntegrationExtension<Set<String>> {

  public static final String NAME = "cacheKeyParameters";

  private final Set<String> cacheKeyParameters;

  public IntegrationCacheKeyParametersExtension() {
    this(null);
  }

  public IntegrationCacheKeyParametersExtension(@Nullable Set<String> cacheKeyParameters) {
    this.cacheKeyParameters = Objects.requireNonNullElse(cacheKeyParameters, new HashSet<>());
  }

  public IntegrationCacheKeyParametersExtension cacheKeyParameter(String parameter) {
    if (StringUtils.hasText(parameter))
      this.cacheKeyParameters.add(parameter);
    return this;
  }

  public IntegrationCacheKeyParametersExtension cacheKeyParameter(Set<String> parameters) {
    parameters.forEach(this::cacheKeyParameter);
    return this;
  }

  public Set<String> cacheKeyParameters() {
    return cacheKeyParameters;
  }

  public String getExtensionKey() {
    return NAME;
  }

  public Set<String> getExtensionValue() {
    return cacheKeyParameters;
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(cacheKeyParameters);
      return !cacheKeyParameters.isEmpty();
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationCacheKeyParametersExtension)) return false;
    IntegrationCacheKeyParametersExtension that = (IntegrationCacheKeyParametersExtension) object;
    return cacheKeyParameters.equals(that.cacheKeyParameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cacheKeyParameters);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationCacheKeyParametersExtension.class.getSimpleName() + "[", "]")
        .add("cacheKeyParameters=" + cacheKeyParameters)
        .toString();
  }
}
