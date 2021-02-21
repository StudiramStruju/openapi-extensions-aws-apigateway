package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.util;

import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;

public class HttpsUriInfo {
  private final String uri;
  private final RequestMethod requestMethod;

  public HttpsUriInfo(@NotNull Path controllerPath, @NotNull Path methodPath, @NotNull RequestMethod requestMethod) {
    this.uri = UriUtils.resolvePaths(controllerPath.normalize().toString(), methodPath.normalize().toString());
    this.requestMethod = requestMethod;
  }

  public String getUri() {
    return uri;
  }

  public RequestMethod getRequestMethod() {
    return requestMethod;
  }
}
