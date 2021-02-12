package io.nemanjaplavsic.openapi.extensions.aws.apigateway.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
    OpenApiProperties.class,
    ApiInfoProperties.class,
    ExtensionProperties.class,
    AwsProperties.class,
    ApiGatewayProperties.class,
    ApiGatewayServiceProperties.class})
public class PropertySourceLoader {
}
