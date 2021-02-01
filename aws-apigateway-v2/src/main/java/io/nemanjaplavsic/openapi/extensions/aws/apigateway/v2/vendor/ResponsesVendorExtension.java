package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor.response.ResponseVendorExtension;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import springfox.documentation.service.ObjectVendorExtension;

import java.util.List;
import java.util.regex.Pattern;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ResponsesVendorExtension extends ObjectVendorExtension {

  public static final String NAME = "responses";

  public static final Pattern INFORMATION_CODE_PATTERN = Pattern.compile("1\\d{2}");
  public static final Pattern SUCCESS_CODE_PATTERN = Pattern.compile("2\\d{2}");
  public static final Pattern REDIRECT_CODE_PATTERN = Pattern.compile("3\\d{2}");
  public static final Pattern CLIENT_ERROR_CODE_PATTERN = Pattern.compile("4\\d{2}");
  public static final Pattern SERVER_ERROR_CODE_PATTERN = Pattern.compile("5\\d{2}");

  public ResponsesVendorExtension(List<ResponseVendorExtension> properties) {
    super(NAME);
    properties.forEach(this::addProperty);
  }
}
