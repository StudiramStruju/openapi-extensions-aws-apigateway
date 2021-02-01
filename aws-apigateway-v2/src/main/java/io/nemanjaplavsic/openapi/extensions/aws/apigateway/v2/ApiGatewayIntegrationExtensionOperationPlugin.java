package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.ApiGatewayExtensionResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ApiGatewayIntegrationExtensionOperationPlugin implements OperationBuilderPlugin {

  private final List<ApiGatewayExtensionResolver> vendorExtensionResolvers;

  @Override
  public void apply(OperationContext context) {

    final List<VendorExtension> extensions = vendorExtensionResolvers.stream()
        .map(vendorExtensionResolver -> vendorExtensionResolver.resolve(context))
        .filter(vendorExtension -> Objects.nonNull(vendorExtension.getValue()))
        .collect(Collectors.toList());

    if (!extensions.isEmpty()) {
      context.operationBuilder()
          .extensions(extensions);
    }
  }

  @Override
  public boolean supports(DocumentationType delimiter) {
    return SwaggerPluginSupport.pluginDoesApply(delimiter);
  }
}
