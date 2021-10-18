package com.ewell.hk.infrastructure.persistence;


import com.alibaba.fastjson.JSON;
import com.ewell.hk.interfaces.dto.EhrssDT0;
import com.ewell.hk.domain.model.IMessageItineraryInfoRepository;
import com.ewell.hk.domain.model.MessageItineraryBody;
import com.ewell.hk.domain.model.MessageItineraryEntity;
import com.ewell.hk.domain.model.MessageLocation;
import com.ewell.hk.domain.model.MessageStatusEnum;
import com.ewell.hk.domain.shared.PrimaryId;
import com.ewell.hk.infrastructure.share.UUIdGenId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-05 13:03
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageItineraryInfoRepositoryImplTest {

  MessageItineraryEntity messageItineraryEntity;

  @Autowired
  private IMessageItineraryInfoRepository esbMessageItineraryInfoRepository;

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
  public void testSave_pass() {
    esbMessageItineraryInfoRepository.save(messageItineraryEntity);
  }

  @Test
  public void testFind_pass() throws Exception {
    esbMessageItineraryInfoRepository.save(messageItineraryEntity);
    MessageItineraryEntity dbMessage = esbMessageItineraryInfoRepository
        .find(messageItineraryEntity.identity());
  }
}