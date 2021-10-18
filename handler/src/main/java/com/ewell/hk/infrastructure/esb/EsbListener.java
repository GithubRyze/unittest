package com.ewell.hk.infrastructure.esb;

import com.ewell.mq.queue.MessageEntity;
import com.ewell.mq.queue.QueueTools;
import com.ewell.mq.queue.async.EwellMQMutiImpl;
import com.ewell.mq.queue.async.QueueCallback;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

/**
 * <h3>esb-handler</h3>
 * <p>esb队列监听</p>
 *
 * @author : 刘理根
 * @date : 2021-08-02 09:15
 **/
@Slf4j
public class EsbListener extends QueueCallback {

  private String esbQueueName;
  private String esbQueueManagerName;
  private EwellMQMutiImpl mutiImpl;

  @Override
  public void onMessage(MessageEntity messageEntity) throws Exception {
    log.info("消息内容：" + messageEntity.getMsg());

  }

  @Override
  public void onException(Exception e) {

  }

  /**
   * .开启ESB队列监听
   *
   * @param esbQueueName 队列名
   * @param esbQueueManagerName 队列管理名
   */
  public void startEsbListener(@NotBlank String esbQueueName,
      @NotBlank String esbQueueManagerName) {
    Validate.notBlank(esbQueueName, "");
    Validate.notBlank(esbQueueManagerName, "");
    this.esbQueueName = esbQueueName;
    this.esbQueueManagerName = esbQueueManagerName;
    QueueTools queueTools = new QueueTools();
    mutiImpl = queueTools.getMutiMQImpl();
    mutiImpl.init(esbQueueManagerName, esbQueueName);
    log.info("开启监听:队列管理器{}, 队列名称:{}",esbQueueManagerName, esbQueueName);
    mutiImpl.run(this);
  }

  /**
   * .取消ESB队列监听
   *
   */
  private void cancelEsbListener() throws Exception {
    this.mutiImpl.quit();
  }

}
