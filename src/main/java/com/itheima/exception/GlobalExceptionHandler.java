package com.itheima.exception;

import cn.hutool.core.util.StrUtil;
import com.itheima.pojo.Result;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {

        e.printStackTrace();
        return Result.error(StrUtil.isEmptyIfStr(e.getMessage()) ? "账号或密码长度不满足要求" : "操作失败");
    }
}
