package com.ewell.hk.domain.model;

import com.ewell.hk.domain.shared.ValueObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * <h3>ESBHandler</h3>
 * <p>消息内容模型</p>
 *
 * @author : 刘理根
 * @date : 2021-08-05 10:42
 **/
public class MessageItineraryBody implements ValueObject<MessageLocation> {

  public String getMessageContent() {
    return messageContent;
  }

  private String messageContent;

  public MessageItineraryBody(String messageContent) {
    this.messageContent = messageContent;
  }

  /**
   * Value objects compare by the values of their attributes, they don't have an identity.
   *
   * @param other The other value object.
   * @return <code>true</code> if the given value object's and this value object's attributes are
   * the same.
   */
  @Override
  public boolean sameValueAs(MessageLocation other) {
    return false;
  }

  /**
   * Value objects can be freely copied.
   *
   * @return A safe, deep copy of this value object.
   */
  @Override
  public MessageLocation copy() {
    return null;
  }

  /**
   *  .初始化一个发送ESB消息的内容
   * @param esbMessageLocation ESB消息位置信息
   * @param jsonString ESB消息内容信息
   * @param originSysCode esb
   * @param goalSysCode esb
   * @return MessageItineraryBody;
   */
  public MessageItineraryBody initSendEsbMessageBody(MessageLocation esbMessageLocation, String jsonString,
      String originSysCode, String goalSysCode) {
    Document document = DocumentHelper.createDocument();
    Element root = document.addElement("ESBEntry");
    Element accessControl = root.addElement("AccessControl");
    Element sysFlag = accessControl.addElement("SysFlag");
    sysFlag.setText("1");
    Element userName = accessControl.addElement("UserName");
    userName.setText(esbMessageLocation.getLocationName());
    Element password = accessControl.addElement("Password");
    password.setText(esbMessageLocation.getLocationPassword());
    Element fid = accessControl.addElement("Fid");
    fid.setText(esbMessageLocation.getLocationUrl());
    Element messageHeader = root.addElement("MessageHeader");
    Element mfid = messageHeader.addElement("Fid");
    mfid.setText(esbMessageLocation.getLocationUrl());
    Element sourceSysCode = messageHeader.addElement("SourceSysCode");
    Element returnFlag = messageHeader.addElement("ReturnFlag");
    returnFlag.setText("0");
    Element targetSysCode = messageHeader.addElement("TargetSysCode");
    sourceSysCode.setText(originSysCode);
    targetSysCode.setText(goalSysCode);
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateStr = sdf.format(date);
    Element msgDate = messageHeader.addElement("MsgDate");
    msgDate.setText(dateStr);
    Element msgInfo = root.addElement("MsgInfo");
    Element msg = msgInfo.addElement("Msg");
    msg.addAttribute("id", "1");
    msg.addAttribute("lastUpdate", dateStr);
    msg.addAttribute("action", "insert");
    Element data = msg.addElement("DATA");
    data.setText("");
    data.addCDATA(jsonString);
    this.messageContent = document.getRootElement().asXML();
    return this;
  }

  @Override
  public String toString() {
    return "MessageItineraryBody{" +
        "messageContent='" + messageContent + '\'' +
        '}';
  }
}
