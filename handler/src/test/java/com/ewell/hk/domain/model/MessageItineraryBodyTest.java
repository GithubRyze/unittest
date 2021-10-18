package com.ewell.hk.domain.model;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-05 12:48
 **/
public class MessageItineraryBodyTest {

  @Test
  public void initSendEsbMessageBody_pass() {
    MessageItineraryBody messageItineraryBody = new MessageItineraryBody("");
    MessageLocation messageLocationTarget = new MessageLocation("QMGR.P90", "PS10071", "TY", "TY",
        "ESB 队列服务");
    messageItineraryBody
        .initSendEsbMessageBody(messageLocationTarget, "Test message", "S90", "S35");
    String esbMessage = messageItineraryBody.getMessageContent();
    Document document = null;
    try {
      document = DocumentHelper.parseText(esbMessage);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    Element messageHeader = document.getRootElement().element("MessageHeader");
    String sourceSysCode = messageHeader.element("SourceSysCode").getText();
    Assertions.assertEquals( "S90", sourceSysCode);
    String targetSysCode = messageHeader.element("TargetSysCode").getText();
    Assertions.assertEquals("S35", targetSysCode);

    Element accessControl = document.getRootElement().element("AccessControl");
    String userName = accessControl.element("UserName").getText();
    Assertions.assertEquals("TY", userName);
    String password = accessControl.element("Password").getText();
    Assertions.assertEquals("TY", password);
    String fid = accessControl.element("Fid").getText();
    Assertions.assertEquals("PS10071", fid );

    Element msgInfo = document.getRootElement().element("MsgInfo");
    Element msg = msgInfo.element("Msg");
    Element data = msg.element("DATA");
    Assertions.assertEquals( "Test message", data.getText());
  }
}