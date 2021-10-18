package com.ewell.hk.interfaces.dto;

import java.io.Serializable;
import org.apache.commons.lang3.Validate;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-03 09:29
 **/
public class EhrssDT0 implements Serializable {

  private String cumcNo;
  private String ehrNo;

  public String getCumcNo() {
    return cumcNo;
  }

  public void setCumcNo(String cumcNo) {
    this.cumcNo = cumcNo;
  }

  public String getEhrNo() {
    return ehrNo;
  }

  public void setEhrNo(String ehrNo) {
    this.ehrNo = ehrNo;
  }

  public void validateParams() {
    Validate.notBlank(cumcNo, "cumcNo 不能为空");
    Validate.notBlank(ehrNo, "ehrNo 不能为空");
  }

  @Override
  public String toString() {
    return "EhrssDT0{" +
        "cumcNo='" + cumcNo + '\'' +
        ", ehrNo='" + ehrNo + '\'' +
        '}';
  }
}
