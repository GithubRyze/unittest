package com.ewell.hk.application.dto;

/**
 * <h3>ESBHandler</h3>
 * <p>消息行程传输对象</p>
 *
 * @author : 刘理根
 * @date : 2021-08-04 17:25
 **/
public class MessageDTO {

  /**
   *  .发送方唯一识别
   */
  private final String messageLocationOriginId;
  /**
   *  .接收方唯一识别
   */
  private final String  messageLocationTargetId;
  /**
   *  .消息内容
   */
  private final String messageContent;

  public MessageDTO(String messageLocationOriginId, String messageLocationTargetId,
      String messageContent) {
    this.messageLocationOriginId = messageLocationOriginId;
    this.messageLocationTargetId = messageLocationTargetId;
    this.messageContent = messageContent;
  }

  public String getMessageLocationOriginId() {
    return messageLocationOriginId;
  }

  public String getMessageLocationTargetId() {
    return messageLocationTargetId;
  }

  public String getMessageContent() {
    return messageContent;
  }
}
