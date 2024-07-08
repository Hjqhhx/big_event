package com.itheima.aop;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.itheima.annocation.AutoFill;
import com.itheima.enumeration.OperationType;
import com.itheima.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 该类用于在对数据库表中数据进行插入或修改时增加时间一列的数据
 */

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 自定义切点表达式
     * 扫描Mapper包下带有AutoFill注解的方法
     */
    @Pointcut("execution (* com.itheima.mapper.*.*(..)) && @annotation(com.itheima.annocation.AutoFill)")
    public void pt() {
    }

    /**
     * 定义切面，在符合上面切入点表达式的操作之前进行autoFill操作，类似于动态代理。
     */
    @Before("pt()")
    public void autoFill(JoinPoint joinPoint) {

        //获取当前被拦截到方法上的自定义注解中标识的数据库操作类型，根据不同操作类型来选择要插入的数据
        //获取拦截到方法的签名对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法的对象，在调用方法的函数，获取方法上指定类型的注解对象
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        //获取注解对象中的value的属性
        OperationType operationValue = annotation.value();

        //获取当前被拦截到方法的参数--实体对象
        Object[] args = joinPoint.getArgs();
        //判断参数是否为空
        if (ArrayUtil.isEmpty(args)) {
            return;
        }

        // 这里相当于一个约定，我们在调用Mapper层的方法时，第一个参数我们传递表对象类型的，所以我们获取第一个参数
        // 又因为我们不只是对员工类型进行增改操作，还有可能是文章，等等都需要，所以获取的参数我们设置为Object类型
        Object entry = args[0];

        //准备赋值的数据
        LocalDateTime time = LocalDateTime.now();
        //获取当前用户的id
        Map<String, Object> clams = ThreadLocalUtil.get();
        Integer id = (Integer) clams.get("id");
        //判断当前进行的是更新还是新增操作，根据操作类型不同给不同的实体类属性赋值
        if (operationValue == OperationType.INSERT) {
            //调用的是插入方法时执行的逻辑
            try {
                //通过反射获取上面entry中的方法

                //用反射获取entry实体类中的名为setCreateTime，参数类型为LocalDateTime的方法
                Method setCreateTime = entry.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                //用反射获取entry实体类中的名为setUpdateTime，参数类型为LocalDateTime的方法
                Method setUpdateTime = entry.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);

                //使用反射调用对象内的方法，为该变量赋值
                //第一个参数是要调用的目标对象，第二个是要用该方法赋的值
                setCreateTime.invoke(entry, time);
                setUpdateTime.invoke(entry, time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (operationValue == OperationType.UPDATE) {
            //调用的是修改方法时执行的逻辑

            try {
                //用反射获取entry实体类中的名为setUpdateTime，参数类型为LocalDateTime的方法
                Method setUpdateTime = entry.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                //获取设置id的方法
                Method setId = entry.getClass().getDeclaredMethod("setId", Integer.class);
                //赋值
                setUpdateTime.invoke(entry, time);
                setId.invoke(entry, id);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
