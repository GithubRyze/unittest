package com.ewell.hk.infrastructure.share;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-04 13:34
 **/
public class CommonResultTest {

  @Test
  public void wrapSuccessfulResult() {
    CommonResult result = CommonResult.wrapSuccessfulResult("test");
    Assertions.assertEquals(result.getData(), "test");
    Assertions.assertEquals(result.getCode(), CommonResult.SUCCESS_CODE);
  }

  @Test
  public void testWrapSuccessfulResult() {
    CommonResult result = CommonResult.wrapSuccessfulResult("test", "success message");
    Assertions.assertEquals(result.getData(), "test");
    Assertions.assertEquals(result.getCode(), CommonResult.SUCCESS_CODE);
    Assertions.assertEquals(result.getMessage(), "success message");
  }

  @Test
  public void wrapErrorResult() {
    CommonResult result = CommonResult.wrapErrorResult("error message");
    Assertions.assertEquals(result.getCode(), CommonResult.ERROR_CODE);
    Assertions.assertEquals(result.getMessage(), "error message");
  }

  @Test
  public void testWrapErrorResult() {
    CommonResult result = CommonResult.wrapErrorResult(100, "error message");
    Assertions.assertEquals(result.getCode(), 100);
    Assertions.assertEquals(result.getMessage(), "error message");
  }
}