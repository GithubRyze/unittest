package com.ewell.hk.interfaces.facade.internal;

import com.alibaba.fastjson.JSON;
import com.ewell.hk.application.IMessageService;
import com.ewell.hk.application.dto.MessageDTO;
import com.ewell.hk.application.dto.MessageSendResult;
import com.ewell.hk.interfaces.dto.EhrssDT0;
import com.ewell.hk.interfaces.dto.MessageSendResultDTO;
import com.ewell.hk.interfaces.facade.IMessageHandleServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-02 18:20
 **/
@Service
public class MessageHandleServiceFacadeImpl implements IMessageHandleServiceFacade {

  @Autowired
  private IMessageService messageService;

  /**
   * .处理Ehrss信息
   */
  @Override
  public MessageSendResultDTO handleEhrssMessage(EhrssDT0 ehrDto) {
    String msg = JSON.toJSONString(ehrDto);
    MessageDTO messageDTO = new MessageDTO("EHRSS", "ESB", msg);
    MessageSendResult messageSendResult = messageService.sendMessage(messageDTO);
    return new MessageSendResultDTO(messageSendResult.getMessageId(),
        messageSendResult.getMessageResult());
  }


  /**
   * .重新发送消息给Esb队列
   *
   * @param primaryKey 消息主键
   * @return false-失败，true-成功
   */
  @Override
  public MessageSendResultDTO resendMsgToEsb(String primaryKey) throws Exception {
    MessageSendResult messageSendResult = messageService.resendMessageInfoByPrimaryKey(primaryKey);
    return new MessageSendResultDTO(messageSendResult.getMessageId(),
        messageSendResult.getMessageResult());
  }
}
