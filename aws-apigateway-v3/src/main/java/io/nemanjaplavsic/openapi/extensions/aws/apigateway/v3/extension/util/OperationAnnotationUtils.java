package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.util.Objects;

@Slf4j
public abstract class OperationAnnotationUtils {

  public static HttpsUriInfo getHttpsUriInfo(HandlerMethod handlerMethod) {
    Path controllerPath = Path.of("");
    final RequestMapping controllerAnnotation = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
    if (Objects.nonNull(controllerAnnotation)) {
      log.warn("RestController '{}' RequestMapping annotation not present", handlerMethod.getBeanType().getSimpleName());
      controllerPath = extractPath(controllerAnnotation.value(), controllerAnnotation.path());
    }

    final RequestMapping requestMappingMethodAnnotation = handlerMethod.getMethodAnnotation(RequestMapping.class);
    if (Objects.nonNull(requestMappingMethodAnnotation)) {
      log.debug("Method '{}' contains RequestMapping annotation", handlerMethod.getMethod().getName());
      return new HttpsUriInfo(controllerPath, extractPath(requestMappingMethodAnnotation.value(), requestMappingMethodAnnotation.path()), extractMethod(controllerAnnotation, requestMappingMethodAnnotation));
    }

    final GetMapping getMappingMethodAnnotation = handlerMethod.getMethodAnnotation(GetMapping.class);
    if (Objects.nonNull(getMappingMethodAnnotation)) {
      log.debug("Method '{}' contains GetMapping annotation", handlerMethod.getMethod().getName());
      return new HttpsUriInfo(controllerPath, extractPath(getMappingMethodAnnotation.value(), getMappingMethodAnnotation.path()), RequestMethod.GET);
    }

    final PostMapping postMappingMethodAnnotation = handlerMethod.getMethodAnnotation(PostMapping.class);
    if (Objects.nonNull(postMappingMethodAnnotation)) {
      log.debug("Method '{}' contains PostMapping annotation", handlerMethod.getMethod().getName());
      return new HttpsUriInfo(controllerPath, extractPath(postMappingMethodAnnotation.value(), postMappingMethodAnnotation.path()), RequestMethod.POST);
    }

    final PutMapping putMappingMethodAnnotation = handlerMethod.getMethodAnnotation(PutMapping.class);
    if (Objects.nonNull(putMappingMethodAnnotation)) {
      log.debug("Method '{}' contains PutMapping annotation", handlerMethod.getMethod().getName());
      return new HttpsUriInfo(controllerPath, extractPath(putMappingMethodAnnotation.value(), putMappingMethodAnnotation.path()), RequestMethod.PUT);
    }

    final PatchMapping patchMappingMethodAnnotation = handlerMethod.getMethodAnnotation(PatchMapping.class);
    if (Objects.nonNull(patchMappingMethodAnnotation)) {
      log.debug("Method '{}' contains PatchMapping annotation", handlerMethod.getMethod().getName());
      return new HttpsUriInfo(controllerPath, extractPath(patchMappingMethodAnnotation.value(), patchMappingMethodAnnotation.path()), RequestMethod.PATCH);
    }

    final DeleteMapping deleteMappingMethodAnnotation = handlerMethod.getMethodAnnotation(DeleteMapping.class);
    if (Objects.nonNull(deleteMappingMethodAnnotation)) {
      log.debug("Method '{}' contains DeleteMapping annotation", handlerMethod.getMethod().getName());
      return new HttpsUriInfo(controllerPath, extractPath(deleteMappingMethodAnnotation.value(), deleteMappingMethodAnnotation.path()), RequestMethod.DELETE);
    }
    throw new RuntimeException("This should never happen");
  }

  public static RequestMethod extractMethod(RequestMapping controllerAnnotation, @NotNull RequestMapping methodAnnotation) {
    if (Objects.nonNull(methodAnnotation)) {
      if (methodAnnotation.method().length != 0) {
        if (methodAnnotation.method().length > 1) {
          throw new RuntimeException(String.format("RequestMapping method cannot have more then ONE RequestMethod %s", extractPath(methodAnnotation.value(), methodAnnotation.path()).toString()));
        }
        return methodAnnotation.method()[0];
      }
    }
    if (controllerAnnotation.method().length != 0) {
      if (controllerAnnotation.method().length > 1) {
        throw new RuntimeException(String.format("RequestMapping method cannot have more then ONE RequestMethod %s", extractPath(controllerAnnotation.value(), controllerAnnotation.path()).toString()));
      }
      return controllerAnnotation.method()[0];
    }

    throw new RuntimeException("RequestMapping method must defined");
  }

  public static Path extractPath(String[] path, String[] value) {
    if (path.length != 0) {
      return Path.of("", path).normalize();
    }
    if (value.length != 0) {
      return Path.of("", value).normalize();
    }
    return Path.of("");
  }
}
