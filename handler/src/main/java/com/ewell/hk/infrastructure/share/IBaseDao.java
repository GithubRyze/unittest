package com.ewell.hk.infrastructure.share;

import java.util.List;
import tk.mybatis.mapper.entity.Example;

/**
 * <h3>bill</h3>
 * <p>基础接口</p>
 *
 * @author : 刘理根
 * @date : 2019-10-12 09:44
 **/
public interface IBaseDao<T, PK> {

  /**
   * 根据主键获取实体
   */
  T selectByPrimaryKey(PK entityId);

  /**
   * 入库
   */
  int insert(T record);

  /**
   * 选择字段插入
   */
  int insertSelective(T record);

  /**
   * 根据主键更新需要更新的字段
   */
  int updateByPrimaryKeySelective(T record);

  /**
   * 根据主键更新所有字段
   */
  int updateByPrimaryKey(T record);

  /**
   * 根据条件查询
   */
  List<T> selectByExample(Example example);


}
