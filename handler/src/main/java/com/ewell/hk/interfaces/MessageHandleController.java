package com.ewell.hk.interfaces;

import com.ewell.hk.infrastructure.share.CommonResult;
import com.ewell.hk.infrastructure.share.HttpResultHandler;
import com.ewell.hk.interfaces.dto.EhrssDT0;
import com.ewell.hk.interfaces.dto.MessageSendResultDTO;
import com.ewell.hk.interfaces.facade.IMessageHandleServiceFacade;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>ESBHandler</h3>
 * <p>esbHandler http接口</p>
 *
 * @author : 刘理根
 * @date : 2021-08-02 15:23
 **/
@Slf4j
@RestController
@RequestMapping("/esb")
public class MessageHandleController {

  @Autowired
  private IMessageHandleServiceFacade esbHandleServiceFacade;

  @HttpResultHandler
  @PostMapping(value = "/ehrss")
  public CommonResult<MessageSendResultDTO> putEhrssMsgToEsb(@RequestBody EhrssDT0 ehrssDto) {
    log.info("收到消息EHRSS:{}", ehrssDto);
    ehrssDto.validateParams();
    MessageSendResultDTO messageSendResultDTO = esbHandleServiceFacade.handleEhrssMessage(ehrssDto);
    return CommonResult.wrapSuccessfulResult(messageSendResultDTO);
  }


  @HttpResultHandler
  @PostMapping(value = "/message")
  public CommonResult<MessageSendResultDTO> sendMessageToEsbById(
      @RequestBody Map<String, String> param)
      throws Exception {
    String primaryId = param.get("primaryId");
    Validate.notBlank(primaryId, "primaryId 不能为空");
    MessageSendResultDTO messageSendResultDTO = esbHandleServiceFacade.resendMsgToEsb(primaryId);
    return CommonResult.wrapSuccessfulResult(messageSendResultDTO);

  }
}
