package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code; //业务状态码 0-成功 1-失败
    private String message; //业务提示信息
    private T data; //响应给前端的数据,data的数据类型是泛型

    //快速返回操作成功的响应结果（带响应数据）
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    //快速返回操作失败的响应结果
    public static <T> Result<T> success() {
        return new Result<>(0, "操作成功", null);
    }

    //快速返回失败的响应结果
    public static <T> Result<T> error(String message) {
        return new Result<>(1, message, null);
    }


}
