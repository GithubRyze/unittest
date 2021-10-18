package com.ewell.hk.domain.shared;

import javax.validation.constraints.NotNull;

/**
 * <h3>ESBHandler</h3>
 * <p>Repository基础接口</p>
 *
 * @author : 刘理根
 * @date : 2021-08-03 12:57
 **/
public interface Repository<T, ID> {

  /**
   * 将一个Aggregate附属到一个Repository，让它变为可追踪。
   */
  void attach(@NotNull T aggregate);

  /**
   * 解除一个Aggregate的追踪
   */
  void detach(@NotNull T aggregate);

  /**
   * 通过ID寻找Aggregate。 找到的Aggregate自动是可追踪的
   */
  T find(@NotNull ID id);

  /**
   * 将一个Aggregate从Repository移除 操作后的aggregate对象自动取消追踪
   */
  void remove(@NotNull T aggregate);

  /**
   * 保存一个Aggregate 保存后自动重置追踪条件
   */
  void save(@NotNull T aggregate);
}
