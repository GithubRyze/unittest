package com.ewell.hk.domain.model;

import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.Validate;

/**
 * <h3>ESBHandler</h3>
 * <p>esb消息状态</p>
 *
 * @author : 刘理根
 * @date : 2021-08-03 17:16
 **/
public enum MessageStatusEnum {

  /**
   * .消息成功
   */
  SUCCESS("0"),
  /**
   * .消息失败
   */
  FAIL("1"),

  /**
   * .待发送
   */
  TO_BE_SEND("2");

  private final String statusCode;

  private MessageStatusEnum(String code) {
    this.statusCode = code;
  }

  public static MessageStatusEnum getEnum(@NotNull Boolean success) {
    Validate.notNull(success, "枚举参数不能未空");
    return success ? SUCCESS : FAIL;
  }

  public static MessageStatusEnum getMessageStatusEnum(@NotNull String statusCode) {
    if (null == statusCode) {
      return null;
    }
    for (MessageStatusEnum temp : MessageStatusEnum.values()) {
      if (temp.getStatusCode().equals(statusCode)) {
        return temp;
      }
    }
    return null;
  }

  public String getStatusCode() {
    return statusCode;
  }

}
