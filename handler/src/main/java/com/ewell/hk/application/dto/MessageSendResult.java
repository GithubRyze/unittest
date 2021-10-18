package com.ewell.hk.application.dto;

import com.ewell.hk.domain.model.MessageStatusEnum;

/**
 * <h3>ESBHandler</h3>
 * <p>消息发送结果</p>
 *
 * @author : 刘理根
 * @date : 2021-08-10 11:33
 **/
public class MessageSendResult {

  private final String messageId;

  private final String messageStatusCode;

  public MessageSendResult(String messageId, MessageStatusEnum messageStatusEnum) {
    this.messageId = messageId;
    this.messageStatusCode = messageStatusEnum.getStatusCode();
  }
  public String getMessageId() {
    return messageId;
  }


  public String getMessageResult() {
    return messageStatusCode;
  }


}
