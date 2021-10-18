package com.ewell.hk.domain.shared;

/**
 * <h3>ESBHandler</h3>
 * <p>主键VO</p>
 *
 * @author : 刘理根
 * @date : 2021-08-02 09:55
 **/
public interface Entity<T, ID> {

  /**
   * Entities compare by identity, not by attributes.
   *
   * @param other The other entity.
   * @return true if the identities are the same, regardless of other attributes.
   */
  boolean sameIdentityAs(T other);

  /**
   * Entities have an identity.
   *
   * @return The identity of this entity.
   */
  ID identity();

}
