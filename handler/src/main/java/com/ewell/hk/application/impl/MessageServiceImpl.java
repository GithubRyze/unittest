package com.ewell.hk.application.impl;

import com.ewell.hk.application.IMessageService;
import com.ewell.hk.application.dto.MessageDTO;
import com.ewell.hk.application.dto.MessageSendResult;
import com.ewell.hk.domain.model.IMessageItineraryInfoRepository;
import com.ewell.hk.domain.model.MessageItineraryBody;
import com.ewell.hk.domain.model.MessageItineraryEntity;
import com.ewell.hk.domain.model.MessageLocation;
import com.ewell.hk.domain.model.MessageStatusEnum;
import com.ewell.hk.domain.service.IEsbMessageService;
import com.ewell.hk.domain.shared.PrimaryId;
import com.ewell.hk.infrastructure.share.UUIdGenId;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-03 12:37
 **/
@Slf4j
@Service
public class MessageServiceImpl implements IMessageService {

  @Autowired
  private IMessageItineraryInfoRepository messageInfoRepository;
  @Autowired
  private IEsbMessageService esbMessageService;

  /**
   * .发送消息
   *
   * @param messageItineraryDTO 消息
   * @return 消息唯一键
   */
  @Override
  public MessageSendResult sendMessage(MessageDTO messageItineraryDTO) {
    // todo 目前暂时写死，后面可能message location数据从DB中获取
    MessageLocation messageLocationOrigin = new MessageLocation("EHRSS", "EHRSS", "EHRSS", "EHRSS",
        "EHRSS");
    MessageLocation messageLocationTarget = new MessageLocation("QMGR.P90", "PS10071", "TY", "TY",
        "ESB 队列服务");
    MessageItineraryBody messageItineraryBody = new MessageItineraryBody("");
    messageItineraryBody
        .initSendEsbMessageBody(messageLocationTarget, messageItineraryDTO.getMessageContent(),
            "S90", "S35");
    String primaryKey = UUIdGenId.genId();
    PrimaryId primaryId = new PrimaryId(primaryKey);
    MessageItineraryEntity messageItineraryEntity = new MessageItineraryEntity(primaryId,
        messageItineraryBody, messageLocationOrigin, messageLocationTarget,
        MessageStatusEnum.TO_BE_SEND);
    boolean success = esbMessageService.sendMessageToEsb(messageItineraryEntity);
    if (success){
      return new MessageSendResult(primaryKey, MessageStatusEnum.SUCCESS);
    }
    return new MessageSendResult(primaryKey, MessageStatusEnum.FAIL);
  }


  /**
   * .重新发送消息给Esb队列
   *
   * @param primaryKey 消息主键
   * @return false-失败，true-成功
   */
  @Override
  public MessageSendResult resendMessageInfoByPrimaryKey(String primaryKey) throws Exception {
    MessageItineraryEntity messageItineraryEntity = messageInfoRepository
        .find(new PrimaryId(primaryKey));
    if (messageItineraryEntity == null) {
      throw new NotFoundException("未找到消息记录");
    }
    boolean success = esbMessageService.sendMessageToEsb(messageItineraryEntity);
    if (success){
      return new MessageSendResult(primaryKey, MessageStatusEnum.SUCCESS);
    }
    return new MessageSendResult(primaryKey, MessageStatusEnum.FAIL);

  }
}
