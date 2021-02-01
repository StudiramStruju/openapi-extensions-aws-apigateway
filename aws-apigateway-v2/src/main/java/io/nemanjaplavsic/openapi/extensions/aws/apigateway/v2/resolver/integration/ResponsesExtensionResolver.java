package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponse;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.IntegrationResponses;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.response.GlobalResponseResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.response.MethodResponseResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration.response.ResponseResolver;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.ResponsesVendorExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseVendorExtension;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResponsesExtensionResolver implements ApiGatewayPropertyExtensionResolver {

  public final GlobalResponseResolver globalResponseResolver;
  public final MethodResponseResolver methodResponseResolver;
  public final ResponseResolver responseResolver;

  @Override
  public ResponsesVendorExtension resolve(OperationContext context) {
    // Collect responses
    final List<ResponseMessage> globalResponseMessages = context.getGlobalResponseMessages(context.httpMethod().name());

    final IntegrationResponses integrationResponseAnnotations = context.findAnnotation(ApiGatewayIntegration.class)
        .transform(ApiGatewayIntegration::responses)
        .orNull();

    final ApiResponse[] apiResponses = context.findAnnotation(ApiResponses.class)
        .transform(ApiResponses::value)
        .orNull();

    final ApiResponse apiResponse = context.findAnnotation(ApiResponse.class).orNull();

    // Process
    if (Objects.nonNull(apiResponse)) {
      if (Objects.nonNull(apiResponses)) {
        final List<ApiResponse> resolvedApiResponses = Arrays.stream(apiResponses)
            .filter(apiRes -> apiRes.code() != apiResponse.code())
            .collect(Collectors.toList());
        resolvedApiResponses.add(apiResponse);
        return resolveResponses(resolvedApiResponses, globalResponseMessages, integrationResponseAnnotations.value());

      } else {
        final ResponseVendorExtension responseVendorExtension;
        if (Objects.isNull(integrationResponseAnnotations)) {
          responseVendorExtension = resolveResponse(apiResponse, globalResponseMessages);
        } else {
          responseVendorExtension = resolveResponse(apiResponse, globalResponseMessages, integrationResponseAnnotations.value());
        }
        return new ResponsesVendorExtension(List.of(responseVendorExtension));
      }

    } else {
      if (Objects.nonNull(apiResponses)) {
        return resolveResponses(Arrays.asList(apiResponses), globalResponseMessages, integrationResponseAnnotations.value());
      } else {
        return resolveResponses(globalResponseMessages, integrationResponseAnnotations);
      }
    }
  }

  /**
   * @param globalResponseMessages
   * @param integrationResponseAnnotations
   * @return ResponsesVendorExtension
   */
  public ResponsesVendorExtension resolveResponses(List<ResponseMessage> globalResponseMessages, @Nullable IntegrationResponses integrationResponseAnnotations) {
    if (globalResponseMessages.isEmpty()) {
      if (Objects.isNull(integrationResponseAnnotations)) {
        return new ResponsesVendorExtension(List.of());
      } else {
        final List<ResponseVendorExtension> responses = Arrays.stream(integrationResponseAnnotations.value())
            .map(responseResolver::resolve)
            .collect(Collectors.toList());
        return new ResponsesVendorExtension(responses);
      }

    } else {
      final List<ResponseVendorExtension> responses;
      if (Objects.isNull(integrationResponseAnnotations)) {
        responses = globalResponseMessages.stream()
            .map(responseResolver::resolve)
            .collect(Collectors.toList());

      } else {
        responses = globalResponseMessages.stream()
            .map(responseMessage -> {

              final Optional<IntegrationResponse> responseAnnotation = Arrays.stream(integrationResponseAnnotations.value())
                  .filter(annotation -> Pattern.matches(annotation.responseStatusPattern(), String.valueOf(responseMessage.getCode())))
                  .findFirst();

              return responseAnnotation.map(response -> responseResolver.resolve(responseMessage, response))
                  .orElseGet(() -> responseResolver.resolve(responseMessage));
            })
            .collect(Collectors.toList());
      }
      return new ResponsesVendorExtension(responses);
    }
  }


  /**
   * @param resolvedApiResponses
   * @param globalResponseMessages
   * @param integrationResponseAnnotations
   * @return
   */
  public ResponsesVendorExtension resolveResponses(List<ApiResponse> resolvedApiResponses,
                                                   List<ResponseMessage> globalResponseMessages,
                                                   @Nullable IntegrationResponse[] integrationResponseAnnotations) {

    final List<ResponseVendorExtension> responsesVendorExtensions = resolvedApiResponses.stream()
        .map(resolvedApiResponse -> resolveResponse(resolvedApiResponse, globalResponseMessages, integrationResponseAnnotations))
        .collect(Collectors.toList());

    return new ResponsesVendorExtension(responsesVendorExtensions);
  }

  /**
   * @param resolvedApiResponse
   * @param globalResponseMessages
   * @return SINGLE ResponseVendorExtension
   */
  public ResponseVendorExtension resolveResponse(ApiResponse resolvedApiResponse,
                                                 List<ResponseMessage> globalResponseMessages) {
    final Optional<ResponseMessage> responseMessage = globalResponseMessages.stream()
        .filter(globalResponseMessage ->
            globalResponseMessage.getCode() == resolvedApiResponse.code())
        .findFirst();

    return responseMessage
        .map(message -> responseResolver.resolve(resolvedApiResponse, message))
        .orElseGet(() -> responseResolver.resolve(resolvedApiResponse));
  }

  /**
   * @param resolvedApiResponse
   * @param globalResponseMessages
   * @param integrationResponseAnnotations
   * @return SINGLE ResponseVendorExtension
   */
  public ResponseVendorExtension resolveResponse(ApiResponse resolvedApiResponse,
                                                 List<ResponseMessage> globalResponseMessages,
                                                 @Nullable IntegrationResponse[] integrationResponseAnnotations) {
    if (globalResponseMessages.isEmpty()) {

      if (Objects.isNull(integrationResponseAnnotations)) {
        return responseResolver.resolve(resolvedApiResponse);
      } else {
        final Optional<IntegrationResponse> responseAnnotation = Arrays.stream(integrationResponseAnnotations)
            .filter(annotation -> Pattern.matches(annotation.responseStatusPattern(), String.valueOf(resolvedApiResponse.code())))
            .findFirst();

        return responseAnnotation
            .map(response -> responseResolver.resolve(resolvedApiResponse, response))
            .orElseGet(() -> responseResolver.resolve(resolvedApiResponse));

      }
    } else {
      if (Objects.isNull(integrationResponseAnnotations)) {
        final Optional<ResponseMessage> globalResponseMessage = globalResponseMessages.stream()
            .filter(responseMessage -> responseMessage.getCode() == resolvedApiResponse.code())
            .findFirst();

        return globalResponseMessage
            .map(responseMessage -> responseResolver.resolve(resolvedApiResponse, responseMessage))
            .orElseGet(() -> responseResolver.resolve(resolvedApiResponse));
      } else {
        final Optional<IntegrationResponse> responseAnnotation = Arrays.stream(integrationResponseAnnotations)
            .filter(annotation -> Pattern.matches(annotation.responseStatusPattern(), String.valueOf(resolvedApiResponse.code())))
            .findFirst();
        final Optional<ResponseMessage> globalResponseMessage = globalResponseMessages.stream()
            .filter(responseMessage -> responseMessage.getCode() == resolvedApiResponse.code())
            .findFirst();

        return responseAnnotation
            .map(response -> globalResponseMessage
                .map(responseMessage -> responseResolver.resolve(resolvedApiResponse, response, responseMessage))
                .orElseGet(() -> responseResolver.resolve(resolvedApiResponse, response)))
            .orElseGet(() -> globalResponseMessage
                .map(responseMessage -> responseResolver.resolve(resolvedApiResponse, responseMessage))
                .orElseGet(() -> responseResolver.resolve(resolvedApiResponse)));
      }
    }
  }


}


