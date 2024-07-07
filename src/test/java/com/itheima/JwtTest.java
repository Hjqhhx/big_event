package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /**
     * 生成jwt
     */
    @Test
    public void testGen() {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("username", "hjq");
        //测试生成jwt令牌
        String token = JWT.create()
                .withClaim("user", claims) //添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) //添加过期时间
                .sign(Algorithm.HMAC256("hjqxh")); //指定算法，配置秘钥

        System.err.println(token);

    }

    @Test
    public void testParse() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoiaGpxIn0sImV4cCI6MTcyMDQ0NDA2OH0" +
                ".hJ8WXlBPidPX8kNYUafQvNnBc0LI6uQBwaHiv_K4uIY";

        //使用生成jwt时指定的算法和秘钥生成一个解析token的对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("hjqxh")).build();

        //使用该对象解析上面的token
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        //获取存入token中的载荷
        Map<String, Claim> claims = decodedJWT.getClaims();
        //从载荷中获取我们自己存入的信息
        Claim user = claims.get("user");
        System.out.println(user);
    }
}
