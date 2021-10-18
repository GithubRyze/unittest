package com.ewell.hk.domain.model;

import com.ewell.hk.domain.shared.ValueObject;

/**
 * <h3>ESBHandler</h3>
 * <p>消息行程位置</p>
 *
 * @author : 刘理根
 * @date : 2021-08-03 17:16
 **/
public class MessageLocation implements ValueObject<MessageLocation> {

  private String locationHost;
  private String locationUrl;
  private String locationName;
  private String locationPassword;

  public String getLocationHost() {
    return locationHost;
  }

  public String getLocationUrl() {
    return locationUrl;
  }

  public String getLocationName() {
    return locationName;
  }

  public String getLocationPassword() {
    return locationPassword;
  }

  private String locationDescription;

  public MessageLocation(String locationHost, String locationUrl, String locationName,
      String locationPassword, String locationDescription) {
    this.locationHost = locationHost;
    this.locationUrl = locationUrl;
    this.locationName = locationName;
    this.locationPassword = locationPassword;
    this.locationDescription = locationDescription;
  }

  /**
   * Value objects compare by the values of their attributes, they don't have an identity.
   *
   * @param other The other value object.
   * @return <code>true</code> if the given value object's and this value object's attributes are
   * the same.
   */
  @Override
  public boolean sameValueAs(MessageLocation other) {
    return false;
  }

  /**
   * Value objects can be freely copied.
   *
   * @return A safe, deep copy of this value object.
   */
  @Override
  public MessageLocation copy() {
    return null;
  }


  @Override
  public String toString() {
    return "MessageLocation{" +
        "locationHost='" + locationHost + '\'' +
        ", locationUrl='" + locationUrl + '\'' +
        ", locationName='" + locationName + '\'' +
        ", locationPassword='" + locationPassword + '\'' +
        ", locationDescription='" + locationDescription + '\'' +
        '}';
  }
}
