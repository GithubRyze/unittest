package com.ewell.hk.application.impl;

import com.alibaba.fastjson.JSON;
import com.ewell.hk.application.dto.MessageDTO;
import com.ewell.hk.application.dto.MessageSendResult;
import com.ewell.hk.domain.model.MessageItineraryBody;
import com.ewell.hk.domain.model.MessageItineraryEntity;
import com.ewell.hk.domain.model.MessageLocation;
import com.ewell.hk.domain.model.MessageStatusEnum;
import com.ewell.hk.domain.service.IEsbMessageService;
import com.ewell.hk.domain.shared.PrimaryId;
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
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-04 08:59
 **/
@Slf4j
@RunWith(PowerMockRunner.class)
public class EsbMessageServiceImplTest {

  @InjectMocks
  MessageServiceImpl messageService;

  @Mock
  MessageItineraryInfoRepositoryImpl esbMessageInfoRepository;

  @Mock
  IEsbMessageService esbMessageService;

  @Before
  public void setUp() {

  }


  @Test
  @PrepareForTest({MessageServiceImpl.class, PrimaryId.class, MessageLocation.class,
      MessageItineraryBody.class,
      MessageDTO.class, MessageItineraryEntity.class, UUIdGenId.class})
  public void sendEsbMessage_pass() throws Exception {

    EhrssDT0 ehrssDT0 = new EhrssDT0();
    ehrssDT0.setEhrNo("1212");
    ehrssDT0.setCumcNo("1221");
    String msg = JSON.toJSONString(ehrssDT0);

    PrimaryId stu = Mockito.mock(PrimaryId.class);
    MessageLocation messageLocationOrigin = Mockito.mock(MessageLocation.class);
    MessageLocation messageLocationTarget = Mockito.mock(MessageLocation.class);
    MessageItineraryEntity messageItineraryEntity = Mockito.mock(MessageItineraryEntity.class);
    MessageItineraryBody messageItineraryBody = Mockito.mock(MessageItineraryBody.class);
    MessageDTO messageItineraryDTO = Mockito.mock(MessageDTO.class);

    String primaryKey = UUIdGenId.genId();
    PowerMockito.mockStatic(UUIdGenId.class);
    PowerMockito
        .when(UUIdGenId.genId()).thenReturn(primaryKey);
    // whenNew 需要注入执行new对象的目标class，这里是（MessageServiceImpl.class）
    PowerMockito.whenNew(PrimaryId.class).withArguments(primaryKey).thenReturn(stu);

    PowerMockito.whenNew(MessageLocation.class).withArguments("EHRSS", "EHRSS", "EHRSS", "EHRSS",
        "EHRSS").thenReturn(messageLocationOrigin);

    PowerMockito.whenNew(MessageLocation.class).withArguments("QMGR.P90", "PS10071", "TY", "TY",
        "ESB 队列服务").thenReturn(messageLocationTarget);

    PowerMockito.whenNew(MessageItineraryBody.class).withArguments("")
        .thenReturn(messageItineraryBody);
    // messageItineraryBody.initSendEsbMessageBody(messageLocationTarget, msg, "S90", "S35");

    PowerMockito.whenNew(MessageDTO.class).withArguments("EHRSS", "ESB", msg)
        .thenReturn(messageItineraryDTO);

    PowerMockito.whenNew(MessageItineraryEntity.class).withArguments(stu,
        messageItineraryBody, messageLocationOrigin, messageLocationTarget,
        MessageStatusEnum.TO_BE_SEND).thenReturn(messageItineraryEntity);

    PowerMockito
        .when(esbMessageService.sendMessageToEsb(messageItineraryEntity))
        .thenReturn(true);
    MessageSendResult messageSendResult;
    messageSendResult = messageService.sendMessage(messageItineraryDTO);
    Assertions.assertNotNull(messageSendResult);
    Assertions.assertEquals(primaryKey, messageSendResult.getMessageId());
    Assertions.assertEquals("0", messageSendResult.getMessageResult());

    PowerMockito
        .when(esbMessageService.sendMessageToEsb(messageItineraryEntity))
        .thenReturn(false);
    messageSendResult = messageService.sendMessage(messageItineraryDTO);
    Assertions.assertNotNull(messageSendResult);
    Assertions.assertEquals(primaryKey, messageSendResult.getMessageId());
    Assertions.assertEquals("1", messageSendResult.getMessageResult());
  }
}