package io.nemanjaplavsic.openapi.extensions.aws.apigateway.v2.vendor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;

@Slf4j
class ResponsesVendorExtensionTest {

  @Test
  void testStatusCodeRegExp() {
    final Matcher matcher = ResponsesVendorExtension.SUCCESS_CODE_PATTERN.matcher("210");
    final boolean matches = matcher.matches();
    if(matches) {
      final MatchResult matchResult = matcher.toMatchResult();
      final String group = matchResult.group();
      log.info(matcher.toMatchResult().toString());
    }

    log.info("This was tested using pattern {}", ResponsesVendorExtension.SUCCESS_CODE_PATTERN.pattern());
  }
}
