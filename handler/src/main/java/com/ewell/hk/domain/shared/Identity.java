package com.ewell.hk.domain.shared;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <h3>ESBHandler</h3>
 * <p>主键VO</p>
 *
 * @author : 刘理根
 * @date : 2021-08-02 09:55
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Identity {

}
