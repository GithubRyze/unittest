package com.ewell.hk.domain.service.impl;

import com.ewell.hk.domain.model.IMessageItineraryInfoRepository;
import com.ewell.hk.domain.model.MessageItineraryBody;
import com.ewell.hk.domain.model.MessageItineraryEntity;
import com.ewell.hk.domain.model.MessageLocation;
import com.ewell.hk.domain.model.MessageStatusEnum;
import com.ewell.hk.domain.service.IEsbMessageService;
import com.ewell.hk.infrastructure.esb.EsbSender;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>ESBHandler</h3>
 * <p>esb消息的领域服务，负责消息领域模型和ESB领域模型的逻辑处理</p>
 *
 * @author : 刘理根
 * @date : 2021-08-10 11:02
 **/
@Slf4j
@Service
public class EsbMessageServiceImpl implements IEsbMessageService {

  @Autowired
  private IMessageItineraryInfoRepository messageInfoRepository;

  /**
   * .发送消息到esb服务
   *
   * @param messageItineraryEntity 消息领域模型
   * @return true-成功， false-失败
   */
  @Override
  public boolean sendMessageToEsb(MessageItineraryEntity messageItineraryEntity) {
    MessageStatusEnum messageStatusEnum = MessageStatusEnum.SUCCESS;
    MessageLocation messageLocationTarget = messageItineraryEntity.getMessageTarget();
    MessageItineraryBody messageItineraryBody = messageItineraryEntity.getEsbMessageBody();
    String primaryId = messageItineraryEntity.identity().idString();
    try {
      log.info("发送消息到ESB队列: {}", messageItineraryEntity);
      boolean success = EsbSender
          .putMsgWithId(primaryId, messageItineraryBody.getMessageContent(),
              messageLocationTarget.getLocationHost(), messageLocationTarget.getLocationUrl());
      if (!success) {
        messageStatusEnum = MessageStatusEnum.FAIL;
      }
    } catch (Exception e) {
      log.error("发送ESB消息:{} 异常:{}", messageItineraryEntity, e);
      messageStatusEnum = MessageStatusEnum.FAIL;
    }
    messageItineraryEntity.updateMessageSendResult(messageStatusEnum);
    messageInfoRepository.save(messageItineraryEntity);
    return MessageStatusEnum.SUCCESS.equals(messageItineraryEntity.getMessageStatusEnum());
  }


}
