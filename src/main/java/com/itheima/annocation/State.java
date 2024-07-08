package com.itheima.annocation;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义注解，用来校验Article类里的state需要符合的规则
 */

@Documented
@Target({FIELD}) //声明用在字段上
@Retention(RUNTIME) //运行时有效
@Constraint(validatedBy = {StateValidation.class}) //指定提供校验规则的类

public @interface State {

    String message() default "{jakarta.validation.constraints.NotEmpty.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
