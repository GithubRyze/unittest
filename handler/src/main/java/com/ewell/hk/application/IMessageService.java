package com.ewell.hk.application;

import com.ewell.hk.application.dto.MessageDTO;
import com.ewell.hk.application.dto.MessageSendResult;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-03 12:36
 **/
public interface IMessageService {

  /**
   * .发送Esb消息
   *
   * @param messageItineraryDTO 消息
   * @return 消息发送结果
   */
  MessageSendResult sendMessage(MessageDTO messageItineraryDTO);


  /**
   * .重新发送消息给Esb队列
   *
   * @param primaryKey 消息主键
   * @return 消息发送结果
   */
  public MessageSendResult resendMessageInfoByPrimaryKey(String primaryKey) throws Exception;
}
