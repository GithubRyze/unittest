package com.ewell.hk.infrastructure.share;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-06 14:06
 **/
@Slf4j
@Aspect
@Component
public class HttpResultAspect {

  @Around("@annotation(HttpResultHandler)")
  public Object httpRequestLogAspect(ProceedingJoinPoint joinPoint) throws Throwable {
    Object proceed = null;
    try {
      proceed = joinPoint.proceed();
    } catch (Exception e) {
      log.error("http请求异常:", e);
      return CommonResult.wrapErrorResult(e.getMessage());
    }
    return proceed;
  }
}
