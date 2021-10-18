package com.ewell.hk.infrastructure.persistence.converter;

import com.ewell.hk.domain.model.MessageItineraryBody;
import com.ewell.hk.domain.model.MessageLocation;
import com.ewell.hk.domain.model.MessageItineraryEntity;
import com.ewell.hk.domain.model.MessageStatusEnum;
import com.ewell.hk.domain.shared.PrimaryId;
import com.ewell.hk.infrastructure.persistence.dao.MessageItineraryInfo;
import java.util.Date;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-04 14:31
 **/
public class MessageItineraryInfoConverter {

  public MessageItineraryInfo convertToMessageItineraryInfoDo(
      MessageItineraryEntity messageItineraryEntity) {
    MessageItineraryInfo esbMessageInfoDo = new MessageItineraryInfo();
    esbMessageInfoDo.setId(messageItineraryEntity.identity().idString());
    esbMessageInfoDo.setMessageOriginId("EHRSS");
    esbMessageInfoDo.setMessageTargetId("ESB");
    esbMessageInfoDo.setMessageContent(messageItineraryEntity.getEsbMessageBody().getMessageContent());
    esbMessageInfoDo.setMessageStatus(messageItineraryEntity.getMessageStatusEnum().getStatusCode());
    esbMessageInfoDo.setUpdatedAt(new Date());
    esbMessageInfoDo.setUpdatedBy("System");
    esbMessageInfoDo.setCreatedAt(new Date());
    esbMessageInfoDo.setCreatedBy("System");
    esbMessageInfoDo.setVersion(0);
    return esbMessageInfoDo;

  }

  public MessageItineraryEntity convertToMessageItineraryEntity(
      MessageItineraryInfo esbMessageInfoDo) {
    PrimaryId primaryId = new PrimaryId(esbMessageInfoDo.getId());
    String esbMessageBody = esbMessageInfoDo.getMessageContent();
    MessageLocation messageLocationOrigin = new MessageLocation("EHRSS", "EHRSS", "EHRSS", "EHRSS",
        "EHRSS");
    MessageLocation messageLocationTarget = new MessageLocation("QMGR.P90", "PS10071", "TY", "TY",
        "ESB 队列服务");
    MessageItineraryBody messageItineraryBody = new MessageItineraryBody(esbMessageBody);
    MessageItineraryEntity esbMessageInfo = new MessageItineraryEntity(primaryId,
        messageItineraryBody,
        messageLocationOrigin,messageLocationTarget,
        MessageStatusEnum.getMessageStatusEnum(esbMessageInfoDo.getMessageStatus()));
    return esbMessageInfo;
  }

}
