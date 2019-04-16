package com.graduation.sportsvenue.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 作为返回前端的高复用对象
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServiceResponse<T> {

    //返回的状态码
    private Integer status;

    //返回的提示消息
    private String msg;

    //返回的响应数据
    private T data;

    public ServiceResponse() {
    }

    public ServiceResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ServiceResponse(Integer status) {
        this.status = status;
    }

    public ServiceResponse(T data) {

        this.data = data;
    }

    public ServiceResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }



    @JsonIgnore
    public boolean isSuccess() {
        return this.status == Const.SUCCESS_STATUS;
    }

    public static ServiceResponse createSuccessResponse(String msg) {
        return new ServiceResponse(Const.SUCCESS_STATUS, msg);
    }

    public static ServiceResponse createSuccessResponse(Integer status) {
        return new ServiceResponse(status);
    }

    public static <T> ServiceResponse createSuccessResponse(String msg, T data) {
        return new ServiceResponse(Const.SUCCESS_STATUS, msg, data);
    }

    public static <T> ServiceResponse createErrorResponse(String msg) {
        return new ServiceResponse(Const.ERROR_STATUS, msg);
    }

    public static ServiceResponse createErrorResponse(Integer status, String msg) {
        return new ServiceResponse(status, msg);
    }
    public static ServiceResponse createErrorResponse(Integer status) {
        return new ServiceResponse(status);
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
