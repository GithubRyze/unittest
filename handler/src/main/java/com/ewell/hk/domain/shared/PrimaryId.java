package com.ewell.hk.domain.shared;


import org.apache.commons.lang.Validate;

/**
 * Uniquely identifies
 *  
 */
public final class PrimaryId implements ValueObject<PrimaryId> {

  private String id;

  /**
   * Constructor.
   *
   * @param id Id string.
   */
  public PrimaryId(final String id) {
    Validate.notNull(id);
    this.id = id;
  }

  /**
   * @return String representation of this tracking id.
   */
  public String idString() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PrimaryId other = (PrimaryId) o;

    return sameValueAs(other);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean sameValueAs(PrimaryId other) {
    return other != null && this.id.equals(other.id);
  }

  /**
   * Value objects can be freely copied.
   *
   * @return A safe, deep copy of this value object.
   */
  @Override
  public PrimaryId copy() {
    return null;
  }

  @Override
  public String toString() {
    return id;
  }


}
