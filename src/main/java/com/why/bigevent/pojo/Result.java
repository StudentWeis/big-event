package com.why.bigevent.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一响应结果
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code; //业务状态码  0-成功  1-失败
    private String message; //提示信息
    private T data; //响应数据

    // 快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data) {
        return new Result<E>(0, "操作成功", data);
    }

    // 快速返回操作成功响应结果
    public static Result<String> success() {
        return new Result<String>(0, "操作成功", null);
    }

    public static Result<String> error(String message) {
        return new Result<String>(1, message, null);
    }
}
