package com.itheima.interceptors;

import com.itheima.utils.JwtUtil;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        try {
            //从redis中获取相同的token
            String redisToken = stringRedisTemplate.opsForValue().get(token);

            if (redisToken == null) {
                throw new RuntimeException();
            }
            Map<String, Object> clams = JwtUtil.parseToken(token);
            //调用ThreadLocalUtil工具类，向ThreadLocal中存入token中的载荷
            ThreadLocalUtil.set(clams);
            //放行
            return true;

        } catch (Exception e) {
            response.setStatus(401);
            //不放行
            return false;
        }
    }

    //为了防止内存泄漏，在程序结束的时候，将ThreadLocal中的数据清除
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.info("正在清理ThreadLocal中的数据...");
        ThreadLocalUtil.remove();
    }
}

