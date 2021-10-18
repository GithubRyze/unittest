package com.ewell.hk.interfaces.facade;

import com.ewell.hk.interfaces.dto.EhrssDT0;
import com.ewell.hk.interfaces.dto.MessageSendResultDTO;

/**
 * <h3>ESBHandler</h3>
 * <p>ESBHandler外觀</p>
 *
 * @author : 刘理根
 * @date : 2021-08-02 15:31
 **/
public interface IMessageHandleServiceFacade {

  /**
   * .处理Ehrss信息
   *
   * @param ehrDto 消息体
   * @return 消息发送结果
   */
  public MessageSendResultDTO handleEhrssMessage(EhrssDT0 ehrDto);


  /**
   * .重新发送消息给Esb队列
   *
   * @param primaryKey 消息主键
   * @return 消息发送结果
   */
  public MessageSendResultDTO resendMsgToEsb(String primaryKey) throws Exception;
}
