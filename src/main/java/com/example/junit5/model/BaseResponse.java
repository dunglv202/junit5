package com.example.junit5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
  private Integer code;
  private Integer status;
  private String message;
  private Object data;

  public static BaseResponse builder() {
    return new BaseResponse();
  }

  public BaseResponse code(Integer code) {
    return new BaseResponse(code, this.status, this.message, this.data);
  }

  public BaseResponse status(Integer status) {
    return new BaseResponse(this.code, status, this.message, this.data);
  }

  public BaseResponse message(String message) {
    return new BaseResponse(this.code, this.status, message, this.data);
  }

  public BaseResponse data(Object data) {
    return new BaseResponse(this.code, this.status, this.message, data);
  }
}
