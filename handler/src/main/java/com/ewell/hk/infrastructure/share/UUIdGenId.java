package com.ewell.hk.infrastructure.share;

import java.util.UUID;

public class UUIdGenId {


  public static String genId() {
    return UUID.randomUUID().toString().toUpperCase().replace("-", "");
  }

}
