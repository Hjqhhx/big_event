package com.itheima.validation;

import com.itheima.annocation.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/*
    1.实现ConstraintValidator接口
    2.第一个参数指定给哪个自定义的类实现校验规则
    3.第二个参数，校验的数据类型
 */
public class StateValidation implements ConstraintValidator<State, String> {


    /**
     * 实现该方法，用于指定校验的规则
     *
     * @param value   将来要校验的数据
     * @param context context in which the constraint is evaluated
     * @return 返回false表示校验不通过，true表示校验通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }
        if (value.equals("已发布") || value.equals("草稿")) {
            return true;
        }
        return false;
    }
}
