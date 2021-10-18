package com.ewell.hk.infrastructure.esb;

import com.ewell.mq.queue.QueueTools;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;


/**
 * <h3>esb-handler</h3>
 * <p>esb工具</p>
 *
 * @author : 刘理根
 * @date : 2021-08-02 09:15
 **/
public class EsbSender {


  /**
   * .根据megid获取消息
   */
  public static String getMsgById(String msgid, String queueManager, String queue) {
    String resMsg;
    MQQueueManager mqQueueManager = null;
    try {
      QueueTools queueTools = new QueueTools();
      mqQueueManager = queueTools.connect(queueManager);
      resMsg = queueTools.getMsgById(mqQueueManager, queue, msgid, 10);
      mqQueueManager.disconnect();
    } catch (Exception e) {
      throw new RuntimeException("消息异常：", e);
    } finally {
      disconnect(mqQueueManager);
    }
    return resMsg;
  }

  /**
   * .指定msgid,用于往MQ队列放置消息
   */
  public static boolean putMsgWithId(String msgId, String reqMsg, String queueManager,
      String queue) {
    MQQueueManager mqQueueManager = null;
    try {
      QueueTools queueTools = new QueueTools();
      mqQueueManager = queueTools.connect(queueManager);
      return queueTools.putMsgWithId(mqQueueManager, queue, reqMsg, msgId);
    } catch (Exception e) {
      throw new RuntimeException("消息异常：", e);
    } finally {
      disconnect(mqQueueManager);
    }
  }

  /**
   * .发送消息到Esb
   */
  public static String putMsg(String reqMsg, String queueManager, String queue) {
    MQQueueManager mqQueueManager = null;
    String msgId;
    try {
      QueueTools queueTools = new QueueTools();
      mqQueueManager = queueTools.connect(queueManager);
      msgId = queueTools.putMsg(mqQueueManager, queue, reqMsg);
    } catch (Exception e) {
      throw new RuntimeException("消息异常：", e);
    } finally {
      disconnect(mqQueueManager);
    }
    return msgId;
  }


  private static void disconnect(MQQueueManager mqQueueManager) {
    if (mqQueueManager == null) {
      return;
    }
    try {
      mqQueueManager.disconnect();
    } catch (MQException e) {
      e.printStackTrace();
    }
  }
}
