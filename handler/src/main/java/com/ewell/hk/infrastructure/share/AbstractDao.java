package com.ewell.hk.infrastructure.share;

import com.ewell.hk.domain.shared.Identity;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * <h3>bill</h3>
 * <p>内部数据层抽象类</p>
 *
 * @author : 刘理根
 * @date : 2019-10-12 09:41
 **/
public abstract class AbstractDao<T, PK> implements
    IBaseDao<T, PK> {

  /**
   * 泛型注入
   */
  @Autowired
  private Mapper<T> mapper;


  /**
   * 根据主键获取实体
   */
  @Override
  public T selectByPrimaryKey(PK entityId) {
    return mapper.selectByPrimaryKey(entityId);
  }


  /**
   * 入库
   */
  @Override
  public int insert(T record) {
    return mapper.insert(record);
  }


  /**
   * 选择字段插入
   */
  @Override
  public int insertSelective(T record) {
    return mapper.insertSelective(record);
  }

  /**
   * 根据主键更新需要更新的字段
   */
  @Override
  public int updateByPrimaryKeySelective(T record) {
    return mapper.updateByPrimaryKeySelective(record);
  }

  /**
   * 根据主键更新所有字段
   */
  @Override
  public int updateByPrimaryKey(T record) {
    return mapper.updateByPrimaryKey(record);
  }

  /**
   * 根据条件查询
   */
  @Override
  public List<T> selectByExample(Example example) {
    return mapper.selectByExample(example);
  }

}
