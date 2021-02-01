package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.resolver.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.List;

@Slf4j
@Component
public class IntegrationResponsesResolver implements ApiGatewayPropertyExtensionResolver {

  @Override
  public VendorExtension resolve(OperationContext context) {
    final List<ResponseMessage> globalResponseMessages = context.getGlobalResponseMessages(context.httpMethod().name());



    return null;
  }
}
