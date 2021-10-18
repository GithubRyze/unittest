package com.ewell.hk.infrastructure.persistence;

import com.ewell.hk.domain.model.MessageItineraryEntity;
import com.ewell.hk.domain.model.IMessageItineraryInfoRepository;
import com.ewell.hk.domain.shared.PrimaryId;
import com.ewell.hk.infrastructure.persistence.converter.MessageItineraryInfoConverter;
import com.ewell.hk.infrastructure.persistence.dao.MessageItineraryInfo;
import com.ewell.hk.infrastructure.share.AbstractDao;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Repository;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-03 17:40
 **/
@Log4j2
@Repository
public class MessageItineraryInfoRepositoryImpl extends AbstractDao<MessageItineraryInfo, String> implements
    IMessageItineraryInfoRepository {


  /**
   * 将一个Aggregate附属到一个Repository，让它变为可追踪。
   */
  @Override
  public void attach(MessageItineraryEntity aggregate) {

  }

  /**
   * 解除一个Aggregate的追踪
   */
  @Override
  public void detach(MessageItineraryEntity aggregate) {

  }

  /**
   * 通过ID寻找Aggregate。 找到的Aggregate自动是可追踪的
   */
  @Override
  public MessageItineraryEntity find(PrimaryId primaryId) {
    MessageItineraryInfo esbMessageInfoDo = this.selectByPrimaryKey(primaryId.idString());
    if (esbMessageInfoDo == null){
      log.warn("主键查询:{} 未找到数据记录", primaryId);
      return null;
    }
    MessageItineraryInfoConverter esbMessageInfoConverter = new MessageItineraryInfoConverter();
    return esbMessageInfoConverter.convertToMessageItineraryEntity(esbMessageInfoDo);
  }

  /**
   * 将一个Aggregate从Repository移除 操作后的aggregate对象自动取消追踪
   */
  @Override
  public void remove(MessageItineraryEntity aggregate) {

  }

  /**
   * 保存一个Aggregate 保存后自动重置追踪条件
   */
  @Override
  public void save(MessageItineraryEntity aggregate) {
    MessageItineraryInfo esbMessageInfoDo = this.selectByPrimaryKey(aggregate.identity().idString());
    MessageItineraryInfoConverter esbMessageInfoConverter = new MessageItineraryInfoConverter();
    if (esbMessageInfoDo == null) {
      this.insert(esbMessageInfoConverter.convertToMessageItineraryInfoDo(aggregate));
    }else {
      this.updateByPrimaryKeySelective(esbMessageInfoConverter.convertToMessageItineraryInfoDo(aggregate));
    }
  }
}
