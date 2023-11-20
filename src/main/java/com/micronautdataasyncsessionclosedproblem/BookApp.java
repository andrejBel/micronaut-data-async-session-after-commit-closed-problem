package com.micronautdataasyncsessionclosedproblem;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class BookApp {
  private String val = "1";

  public String getVal() {
    return val;
  }

  public void setVal(String val) {
    this.val = val;
  }

  @Override
  public String toString() {
    return "BookApp{" +
        "val='" + val + '\'' +
        '}';
  }
}
