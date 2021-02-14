package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.integration.response.IntegrationResponseExtension;
import org.springframework.lang.Nullable;
import springfox.documentation.service.ObjectVendorExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class IntegrationResponsesExtension implements IntegrationExtension<ObjectVendorExtension> {

  public static final String NAME = "responses";

  private final List<IntegrationResponseExtension> responses;

  public IntegrationResponsesExtension() {
    this(null);
  }

  public IntegrationResponsesExtension(@Nullable List<IntegrationResponseExtension> responses) {
    this.responses = Objects.requireNonNullElse(responses, new ArrayList<>());
  }

  public static Builder builder() {
    return new Builder();
  }

  public IntegrationResponsesExtension response(IntegrationResponseExtension responseExtension) {
    if (responseExtension.isValid()) {
      responses.stream()
          .filter(IntegrationResponseExtension::isValid)
          .filter(existing -> existing.matches(responseExtension))
          .findFirst()
          .ifPresentOrElse(
              existing -> existing.update(responseExtension),
              () -> responses.add(responseExtension));
    }
    return this;
  }

  public IntegrationResponsesExtension responses(List<IntegrationResponseExtension> responses) {
    responses.forEach(this::response);
    return this;
  }

  public List<IntegrationResponseExtension> responses() {
    return responses;
  }

  @Override
  public ObjectVendorExtension toVendorExtension() {
    ObjectVendorExtension vendorExtension = new ObjectVendorExtension(NAME);
    responses.stream()
        .filter(IntegrationResponseExtension::isValid)
        .forEach(response -> vendorExtension.addProperty(response.toVendorExtension()));
    return vendorExtension;
  }

  @Override
  public boolean isValid() {
    try {
      Objects.requireNonNull(responses);
      return !responses.isEmpty();
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof IntegrationResponsesExtension)) return false;
    IntegrationResponsesExtension that = (IntegrationResponsesExtension) object;
    return responses.equals(that.responses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responses);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntegrationResponsesExtension.class.getSimpleName() + "[", "]")
        .add("responses=" + responses)
        .toString();
  }

  public static class Builder {
    private List<IntegrationResponseExtension> responses;

    Builder() {
    }

    public Builder responses(List<IntegrationResponseExtension> responses) {
      this.responses = responses;
      return this;
    }

    public IntegrationResponsesExtension build() {
      return new IntegrationResponsesExtension(responses);
    }
  }
}
