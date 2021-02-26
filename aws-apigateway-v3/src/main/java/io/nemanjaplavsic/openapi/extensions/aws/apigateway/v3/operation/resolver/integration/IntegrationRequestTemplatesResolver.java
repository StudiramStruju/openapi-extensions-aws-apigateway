package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationRequestTemplate;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationRequestTemplates;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationRequestTemplatesExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.request.IntegrationRequestTemplateExtension;
import io.swagger.v3.oas.models.Operation;
import org.springframework.web.method.HandlerMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class IntegrationRequestTemplatesResolver implements IntegrationResolver<IntegrationRequestTemplatesExtension> {

  @Override
  public IntegrationRequestTemplatesExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    IntegrationRequestTemplatesExtension extension = new IntegrationRequestTemplatesExtension();

    // Default request template
    Optional.ofNullable(operation.getRequestBody())
        .flatMap(requestBody -> Optional.ofNullable(requestBody.getContent()))
        .ifPresent(content -> content.forEach((mediaTypeValue, mediaType) ->
            extension.template(new IntegrationRequestTemplateExtension(mediaTypeValue, null))
        ));

    // Resolve Annotation parameters
    final List<IntegrationRequestTemplateExtension> annotationParameters = Optional.ofNullable(handlerMethod.getMethodAnnotation(IntegrationRequestTemplates.class))
        .map(templates -> Arrays.stream(templates.value())
            .map(this::from)
            .filter(Objects::nonNull)
            .collect(Collectors.toList()))
        .orElse(new ArrayList<>());
    extension.templates(annotationParameters);

    return extension;
  }

  public IntegrationRequestTemplateExtension from(IntegrationRequestTemplate annotation) {
    return new IntegrationRequestTemplateExtension(
        annotation.mediaType(),
        annotation.template()
    );
  }


}
