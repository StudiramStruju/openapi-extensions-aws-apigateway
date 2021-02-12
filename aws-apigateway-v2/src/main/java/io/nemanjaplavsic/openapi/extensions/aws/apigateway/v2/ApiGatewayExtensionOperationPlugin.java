package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.extension.ApiGatewayIntegrationExtension;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.ApiGatewayIntegrationExtensionResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
@ConditionalOnProperty(value = "openapi.extension.aws.api-gateway.enabled-operation-plugin", havingValue = "true", matchIfMissing = true)
public class ApiGatewayExtensionOperationPlugin implements OperationBuilderPlugin {

  private final ApiGatewayIntegrationExtensionResolver apiGatewayIntegrationExtensionResolver;

  @Override
  public void apply(OperationContext context) {
    final ApiGatewayIntegrationExtension integrationExtension = apiGatewayIntegrationExtensionResolver.resolve(context);
      context.operationBuilder()
          .extensions(List.of(integrationExtension.toVendorExtension()));
  }

  @Override
  public boolean supports(DocumentationType delimiter) {
    return SwaggerPluginSupport.pluginDoesApply(delimiter);
  }
}
