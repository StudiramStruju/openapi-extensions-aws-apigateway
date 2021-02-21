package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v3.extension.util;

import org.springframework.util.StringUtils;

public abstract class UriUtils {

  public static String resolvePaths(String first, String second) {
    final String root = removeSlashBegin(removeSlashEnd(first));
    final String method = removeSlashBegin(removeSlashEnd(second));
    if (StringUtils.hasText(root)) {
      return String.format("/%s/%s", root, method);
    } else {
      return String.format("/%s", method);
    }
  }

  public static String resolveEndpoint(String first, String second) {
    return removeSlashBegin(resolvePaths(first, second));
  }

  public static String removeSlashBegin(String value) {
    if (value.startsWith("/")) {
      return removeSlashBegin(value.substring(1));
    }
    return value;
  }

  public static String removeSlashEnd(String value) {
    if (value.endsWith("/")) {
      return removeSlashEnd(value.substring(0, value.length() - 1));
    }
    return value;
  }
}
