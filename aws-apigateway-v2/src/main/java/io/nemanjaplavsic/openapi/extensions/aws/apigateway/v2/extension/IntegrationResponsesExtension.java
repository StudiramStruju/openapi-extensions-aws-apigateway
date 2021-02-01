package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.ObjectVendorExtension;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class IntegrationResponsesExtension implements IntegrationExtension<ObjectVendorExtension> {

  public static final String NAME = "responses";

  private final List<IntegrationResponseExtension> responses = new ArrayList<>();

  public IntegrationResponsesExtension response(IntegrationResponseExtension responseExtension) {
    responses.removeIf(response -> response.getResponseStatusPattern().equals(responseExtension.getResponseStatusPattern()));
    responses.add(responseExtension);
    return this;
  }

  public IntegrationResponsesExtension response(List<IntegrationResponseExtension> responsesExtensions) {
    responses.removeIf(response -> responsesExtensions.stream()
        .anyMatch(responseExtension -> response.getResponseStatusPattern().equals(responseExtension.getResponseStatusPattern())));
    responses.addAll(responsesExtensions);
    return this;
  }

  @Override
  public ObjectVendorExtension toVendorExtension() {
    ObjectVendorExtension vendorExtension = new ObjectVendorExtension(NAME);
    responses.forEach(response -> vendorExtension.addProperty(response.toVendorExtension()));
    return vendorExtension;
  }
}
