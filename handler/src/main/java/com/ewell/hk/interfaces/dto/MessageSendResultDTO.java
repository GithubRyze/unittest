package com.ewell.hk.interfaces.dto;

/**
 * <h3>ESBHandler</h3>
 * <p>消息发送结果</p>
 *
 * @author : 刘理根
 * @date : 2021-08-10 11:33
 **/
public class MessageSendResultDTO {

  private String messageId;
  private String messageResult;


  public MessageSendResultDTO(String messageId, String messageResult) {
    this.messageId = messageId;
    this.messageResult = messageResult;
  }

  public String getMessageId() {
    return messageId;
  }

  public String getMessageResult() {
    return messageResult;
  }
}
