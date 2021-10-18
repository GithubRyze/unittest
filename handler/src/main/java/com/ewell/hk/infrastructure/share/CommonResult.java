package com.ewell.hk.infrastructure.share;

public class CommonResult<T> {

  public static final int SUCCESS_CODE = 0;
  public static final int ERROR_CODE = 1;


  /**
   * .状态码
   */
  private Integer code;
  /**
   * .响应信息，描述响应成功或者错误原因，如必填参数效验失败
   */
  private String message;
  /**
   * .响应体
   */
  private T data;

  public CommonResult() {

  }

  public static <T> CommonResult<T> wrapSuccessfulResult(T data) {
    CommonResult<T> result = new CommonResult<T>();
    result.data = data;
    result.code = SUCCESS_CODE;
    return result;
  }

  public static <T> CommonResult<T> wrapSuccessfulResult(T data, String message) {
    CommonResult<T> result = new CommonResult<T>();
    result.data = data;
    result.message = message;
    result.code = SUCCESS_CODE;
    return result;
  }

  public static <T> CommonResult<T> wrapErrorResult(String message) {
    CommonResult<T> result = new CommonResult<T>();
    result.code = ERROR_CODE;
    result.message = message;
    return result;
  }

  public static <T> CommonResult<T> wrapErrorResult(Integer code, String message) {
    CommonResult<T> result = new CommonResult<T>();
    result.code = code;
    result.message = message;
    return result;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "CommonResult{" +
        "code=" + code +
        ", message='" + message + '\'' +
        ", data=" + data +
        '}';
  }
}
