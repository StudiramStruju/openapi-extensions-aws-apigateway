package io.nemanjaplavsic.openapi.extensions.aws.apigateway.enumeration;

public interface EnumKey {

  static <T extends EnumKey> T requireNonNullKey(EnumKey first, Class<T> type) {
    if (first.key() == null) {
      throw new NullPointerException(String.format("%s key is NULL", first.getClass().getSimpleName()));
    }
    return type.cast(first);
  }

  static <T extends EnumKey> T requireNonNullKeyOrElse(EnumKey first, EnumKey second, Class<T> type) {
    return (first.key() != null) ? type.cast(first) : requireNonNullKey(second, type);
  }

  String key();
}
