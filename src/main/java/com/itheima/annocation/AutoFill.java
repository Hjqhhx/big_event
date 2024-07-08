package com.itheima.annocation;


import com.itheima.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)


public @interface AutoFill {
    //指定当前对数据库进行的操作类型,便于定义切点表达式
    OperationType value();
}
