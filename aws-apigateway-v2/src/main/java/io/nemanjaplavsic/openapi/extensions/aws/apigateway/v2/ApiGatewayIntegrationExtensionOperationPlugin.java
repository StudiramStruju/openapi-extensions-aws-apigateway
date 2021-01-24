package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration.OpenApiProperties.OpenApiExtensionProperties;
import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.ApiGatewayExtensionResolver;
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
public class ApiGatewayIntegrationExtensionOperationPlugin implements OperationBuilderPlugin {

  private final OpenApiExtensionProperties extensionProperties;
  private final List<ApiGatewayExtensionResolver> vendorExtensionResolvers;

  public ApiGatewayIntegrationExtensionOperationPlugin(OpenApiExtensionProperties extensionProperties,
                                                       List<ApiGatewayExtensionResolver> vendorExtensionResolvers) {
    this.extensionProperties = extensionProperties;
    this.vendorExtensionResolvers = vendorExtensionResolvers;
  }

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

//  void handleOldWay(OperationContext context) {
//    final ApiGatewayIntegration apiGatewayIntegration = context.findAnnotation(ApiGatewayIntegration.class)
//        .orNull();
//
//    context.getParameters();
//    context.getGlobalOperationParameters();
//
//    ApiGatewayIntegrationVendorExtension.builder()
//        .apiGatewayIntegration(apiGatewayIntegration)
//        .httpMethod(context.httpMethod())
//        .requestMappingPattern(context.requestMappingPattern())
//        .properties(extensionProperties.getAws().getApiGateway().getService())
//        .resolvedParameters(context.getParameters())
//        .globalOperationParameters(context.getGlobalOperationParameters())
//        .buildOptional()
//        .ifPresent(extension -> context.operationBuilder().extensions(List.of(extension)));
//  }

  @Override
  public boolean supports(DocumentationType delimiter) {
    return SwaggerPluginSupport.pluginDoesApply(delimiter);
  }
}
