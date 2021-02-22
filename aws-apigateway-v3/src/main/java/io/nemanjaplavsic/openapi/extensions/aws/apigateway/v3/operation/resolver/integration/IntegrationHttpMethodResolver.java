package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.resolver.integration;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.annotations.ApiGatewayIntegration;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration.HttpMethod;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.operation.extension.integration.IntegrationHttpMethodExtension;
import io.swagger.v3.oas.models.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class IntegrationHttpMethodResolver implements IntegrationResolver<IntegrationHttpMethodExtension> {

  private final Map<String, RequestMethodEvaluation> rme = new HashMap<>();

  @Override
  public IntegrationHttpMethodExtension resolve(Operation operation, HandlerMethod handlerMethod) {
    final HttpMethod httpMethod = Optional.ofNullable(handlerMethod.getMethodAnnotation(ApiGatewayIntegration.class))
        .map(ApiGatewayIntegration::httpMethod)
        .orElse(null);

    final String methodName = handlerMethod.getBeanType().getSimpleName() + "#" + handlerMethod.getMethod().getName();
    final RequestMethodEvaluation evaluation = extractRequestMethod(handlerMethod, rme.get(methodName));

    if (evaluation.totalCount() > 2) {
      final String errorMessage = String.format(
          "Method '%s' with operationId '%s' has RequestMapping annotation with method defined with multiple request methods! Only Allowed ONE in combination with OPTIONS request method!",
          handlerMethod.getMethod().getName(),
          operation.getOperationId()
      );
      throw new RuntimeException(errorMessage);
    }

    if (rme.containsKey(methodName) && evaluation.done()) {
      rme.remove(methodName);
      log.debug("Removing request method evaluation entry {}", methodName);
    } else {
      rme.put(methodName, evaluation);
      log.debug("Adding request method evaluation entry {}", methodName);
    }

    return new IntegrationHttpMethodExtension(evaluation.requestMethod(), httpMethod);
  }

  private static class RequestMethodEvaluation {
    private final AtomicInteger count = new AtomicInteger(0);
    private final int totalCount;
    private RequestMethod requestMethod;

    public RequestMethodEvaluation(int totalCount, RequestMethod requestMethod) {
      this.count.incrementAndGet();
      this.totalCount = totalCount;
      this.requestMethod = requestMethod;
    }

    public RequestMethodEvaluation requestMethod(RequestMethod requestMethod) {
      this.count.incrementAndGet();
      this.requestMethod = requestMethod;
      return this;
    }

    public int totalCount() {
      return totalCount;
    }

    public RequestMethod requestMethod() {
      return requestMethod;
    }

    public boolean done() {
      return count.get() >= totalCount();
    }
  }

  public static RequestMethodEvaluation extractRequestMethod(HandlerMethod handlerMethod, @Nullable RequestMethodEvaluation evaluated) {
    if (handlerMethod.hasMethodAnnotation(GetMapping.class)) return new RequestMethodEvaluation(1, RequestMethod.GET);
    if (handlerMethod.hasMethodAnnotation(PostMapping.class)) return new RequestMethodEvaluation(1, RequestMethod.POST);
    if (handlerMethod.hasMethodAnnotation(PutMapping.class)) return new RequestMethodEvaluation(1, RequestMethod.PUT);
    if (handlerMethod.hasMethodAnnotation(DeleteMapping.class)) return new RequestMethodEvaluation(1, RequestMethod.DELETE);
    if (handlerMethod.hasMethodAnnotation(PatchMapping.class)) return new RequestMethodEvaluation(1, RequestMethod.PATCH);
    if (handlerMethod.hasMethodAnnotation(RequestMapping.class)) {
      final RequestMapping methodAnnotation = Objects.requireNonNull(handlerMethod.getMethodAnnotation(RequestMapping.class));
      if (Objects.isNull(evaluated)) {
        return new RequestMethodEvaluation(methodAnnotation.method().length, methodAnnotation.method()[0]);
      } else {
        final RequestMethod requestMethod = Arrays.stream(methodAnnotation.method())
            .filter(method -> !method.equals(evaluated.requestMethod())).findFirst()
            .orElse(null);
        return evaluated.requestMethod(requestMethod);
      }
    }
    throw new RuntimeException(String.format("Failed to evaluate method '%s' for request method!", handlerMethod.getMethod().getName()));
  }

}
