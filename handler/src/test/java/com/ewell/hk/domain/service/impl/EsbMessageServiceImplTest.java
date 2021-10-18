package com.ewell.hk.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.ewell.hk.domain.model.IMessageItineraryInfoRepository;
import com.ewell.hk.domain.model.MessageItineraryBody;
import com.ewell.hk.domain.model.MessageItineraryEntity;
import com.ewell.hk.domain.model.MessageLocation;
import com.ewell.hk.domain.model.MessageStatusEnum;
import com.ewell.hk.domain.service.IEsbMessageService;
import com.ewell.hk.domain.shared.PrimaryId;
import com.ewell.hk.infrastructure.esb.EsbSender;
import com.ewell.hk.infrastructure.persistence.MessageItineraryInfoRepositoryImpl;
import com.ewell.hk.infrastructure.share.UUIdGenId;
import com.ewell.hk.interfaces.dto.EhrssDT0;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-10 12:29
 **/
@Slf4j
@RunWith(PowerMockRunner.class)
class EsbMessageServiceImplTest {

  @InjectMocks
  EsbMessageServiceImpl esbMessageService;

  @Mock
  IMessageItineraryInfoRepository messageInfoRepository;

  MessageItineraryEntity messageItineraryEntity;
  String queueManager = "QMGR.P90";

  public EsbMessageServiceImplTest() {
  }

  @Before
  public void setUp() {
    EhrssDT0 ehrssDT0 = new EhrssDT0();
    ehrssDT0.setEhrNo("1212");
    ehrssDT0.setCumcNo("1221");
    String msg = JSON.toJSONString(ehrssDT0);
    MessageLocation messageLocationOrigin = new MessageLocation("EHRSS", "EHRSS", "EHRSS", "EHRSS",
        "EHRSS");
    MessageLocation messageLocationTarget = new MessageLocation("QMGR.P90", "PS10071", "TY", "TY",
        "ESB 队列服务");
    MessageItineraryBody messageItineraryBody = new MessageItineraryBody("");
    messageItineraryBody.initSendEsbMessageBody(messageLocationTarget, msg, "S90", "S35");
    PrimaryId primaryId = new PrimaryId(UUIdGenId.genId());
    messageItineraryEntity = new MessageItineraryEntity(primaryId,
        messageItineraryBody,
        messageLocationOrigin, messageLocationTarget,
        MessageStatusEnum.TO_BE_SEND);
  }

  @Test
  @PrepareForTest(EsbSender.class)
  public void sendMessageToEsb_pass() {

    PowerMockito.mockStatic(EsbSender.class);
    PowerMockito
        .when(EsbSender
            .putMsgWithId(messageItineraryEntity.identity().idString(),
                messageItineraryEntity.getEsbMessageBody().getMessageContent(), queueManager,
                "PS10071"))
        .thenReturn(true);
    esbMessageService.sendMessageToEsb(messageItineraryEntity);
    Assertions
        .assertEquals(messageItineraryEntity.getMessageStatusEnum(), MessageStatusEnum.SUCCESS);
  }

  @Test
  @PrepareForTest(EsbSender.class)
  public void testSendMessageToEsbFailed_pass() {
    PowerMockito.mockStatic(EsbSender.class);
    PowerMockito
        .when(EsbSender
            .putMsgWithId(messageItineraryEntity.identity().idString(),
                messageItineraryEntity.getEsbMessageBody().getMessageContent(), queueManager,
                "PS10071"))
        .thenReturn(false);
    esbMessageService.sendMessageToEsb(messageItineraryEntity);
    Assertions
        .assertEquals(messageItineraryEntity.getMessageStatusEnum(), MessageStatusEnum.FAIL);
  }

  @Test
  @PrepareForTest(EsbSender.class)
  public void testSendMessageToEsbException_pass() {

    PowerMockito.mockStatic(EsbSender.class);
    PowerMockito
        .when(EsbSender.putMsgWithId(messageItineraryEntity.identity().idString(),
            messageItineraryEntity.getEsbMessageBody().getMessageContent(), queueManager,
            "PS10071"))
        .thenThrow(RuntimeException.class).thenReturn(false);
    esbMessageService.sendMessageToEsb(messageItineraryEntity);
    Assertions
        .assertEquals(messageItineraryEntity.getMessageStatusEnum(), MessageStatusEnum.FAIL);
  }
}