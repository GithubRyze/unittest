package com.ewell.hk.domain.model;

import com.ewell.hk.domain.shared.Entity;
import com.ewell.hk.domain.shared.PrimaryId;
import lombok.extern.slf4j.Slf4j;


/**
 * <h3>ESBHandler</h3>
 * <p>Esb消息</p>
 *
 * @author : 刘理根
 * @date : 2021-08-02 09:55
 **/
@Slf4j
public class MessageItineraryEntity implements Entity<MessageItineraryEntity, PrimaryId> {

  private final PrimaryId primaryId;
  private final MessageItineraryBody messageBody;
  private final MessageLocation messageOrigin;
  private final MessageLocation messageTarget;
  private MessageStatusEnum messageStatusEnum;

  public MessageItineraryEntity(PrimaryId primaryId, MessageItineraryBody messageBody,
      MessageLocation messageOrigin, MessageLocation messageTarget,
      MessageStatusEnum esbMessageStatusEnum) {
    this.primaryId = primaryId;
    this.messageBody = messageBody;
    this.messageOrigin = messageOrigin;
    this.messageTarget = messageTarget;
    this.messageStatusEnum = esbMessageStatusEnum;
  }

  public MessageLocation getMessageOrigin() {
    return messageOrigin;
  }

  public MessageLocation getMessageTarget() {
    return messageTarget;
  }

  public MessageStatusEnum getMessageStatusEnum() {
    return this.messageStatusEnum;
  }


  public MessageItineraryBody getEsbMessageBody() {
    return this.messageBody;
  }


  /**
   * Entities compare by identity, not by attributes.
   *
   * @param other The other entity.
   * @return true if the identities are the same, regardless of other attributes.
   */
  @Override
  public boolean sameIdentityAs(MessageItineraryEntity other) {
    return false;
  }

  /**
   * Entities have an identity.
   *
   * @return The identity of this entity.
   */
  @Override
  public PrimaryId identity() {
    return this.primaryId;
  }

  /**
   *  .更新消息发送结果状态
   * @param messageStatusEnum MessageStatusEnum
   */
  public void updateMessageSendResult(MessageStatusEnum messageStatusEnum){
    this.messageStatusEnum = messageStatusEnum;
  }

  @Override
  public String toString() {
    return "MessageItineraryEntity{" +
        "primaryId=" + primaryId +
        ", messageBody=" + messageBody +
        ", messageOrigin=" + messageOrigin +
        ", messageTarget=" + messageTarget +
        ", messageStatusEnum=" + messageStatusEnum +
        '}';
  }
}
