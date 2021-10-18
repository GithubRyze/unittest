package com.ewell.hk.domain.service;

import com.ewell.hk.domain.model.MessageItineraryEntity;

/**
 * <h3>ESBHandler</h3>
 * <p>esb消息的领域服务，负责消息领域模型和ESB领域模型的逻辑处理</p>
 *
 * @author : 刘理根
 * @date : 2021-08-10 10:59
 **/
public interface IEsbMessageService {

  /**
   * .发送消息到esb服务
   *
   * @param messageItineraryEntity 消息领域模型
   * @return true-成功， false-失败
   * @throws Exception 消息发送异常
   */
  public boolean sendMessageToEsb(MessageItineraryEntity messageItineraryEntity);
}
