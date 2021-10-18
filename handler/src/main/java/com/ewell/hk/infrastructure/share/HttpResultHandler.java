package com.ewell.hk.infrastructure.share;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-06 14:08
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpResultHandler {

}
