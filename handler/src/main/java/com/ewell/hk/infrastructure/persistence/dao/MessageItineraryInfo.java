package com.ewell.hk.infrastructure.persistence.dao;

import java.util.Date;
import javax.persistence.Id;
import lombok.Data;

/**
 * <h3>ESBHandler</h3>
 * <p></p>
 *
 * @author : 刘理根
 * @date : 2021-08-04 14:33
 **/
@Data
public class MessageItineraryInfo {

  @Id
  private String id;
  private String messageOriginId;
  private String messageTargetId;
  private String messageStatus;
  private String messageContent;
  private Date createdAt;
  private String createdBy;
  private Date updatedAt;
  private String updatedBy;
  private Integer version;

}
