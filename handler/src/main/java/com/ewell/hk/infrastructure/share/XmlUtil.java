package com.ewell.hk.infrastructure.share;

import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

/**
 * <h3>esb-handler</h3>
 * <p>XML工具</p>
 *
 * @author : 刘理根
 * @date : 2021-08-02 09:15
 **/
@Slf4j
public class XmlUtil {

  public static String getData(String xml) {
    if (StringUtils.isEmpty(xml)) {
      return null;
    }
    Document document = null;
    try {
      document = DocumentHelper.parseText(xml);
    } catch (DocumentException e) {
      log.error("getData:" + e);
    }
    Element rootElement = document.getRootElement();
    Element msgInfo = rootElement.element("MsgInfo");
    Element msg = msgInfo.element("Msg");
    Element data = msg.element("DATA");
    return data.getText();
  }


  private static Document getXml(String path) {
    SAXReader reader = new SAXReader();
    Document document = null;
    try (InputStream stream = XmlUtil.class.getClassLoader().getResourceAsStream(path)) {
      document = reader.read(stream);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return document;
  }

  public static String getTarget(String msg) {
    Document document = null;
    try {
      document = DocumentHelper.parseText(msg);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    Element messageHeader = document.getRootElement().element("MessageHeader");
    String target = messageHeader.element("SourceSysCode").getText();
    return target;
  }

  public static String buildData(Document document, String str) {
    Element rootElement = document.getRootElement();
    Element msgInfo = rootElement.element("MsgInfo");
    Element msg = msgInfo.element("Msg");
    Element data = msg.element("DATA");
    data.setText("");
    data.addCDATA(str);
    String text = document.getRootElement().asXML();
    return text;
  }

}
