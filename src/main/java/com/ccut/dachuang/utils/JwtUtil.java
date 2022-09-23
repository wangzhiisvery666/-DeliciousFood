package com.ccut.dachuang.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.ccut.dachuang.Exception.CustomizeException;
import com.ccut.dachuang.common.ErrorEnum;
import com.ccut.dachuang.model.pojo.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;

@Service
public class JwtUtil {

    private static final String SECRET="!@#$%^&*";
    public static final String UID="uid";


    /**
     * 生成token
     */
    public static String createToken(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1); //默认令牌过期时间1小时
        JWTCreator.Builder builder = JWT.create();
        // 构建payload
        builder.withClaim("uid", user.getId());
        // 指定过期时间和签名算法
        return builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SECRET));

    }

    /**
     * 解析token
     */
    public static DecodedJWT decodeToken(String token) {
        if (token==null){
            throw  new CustomizeException(ErrorEnum.NOT_LOGGED_IN);
        }
        //获取登录用户真正的密码假如数据库查出来的是123456
//        String password = "admin";
        JWTVerifier build = JWT.require(Algorithm.HMAC256(SECRET)).build();
        return build.verify(token);
    }

    /**
     * 解析token
     */
    public static HashMap<String, String> getDecodeTokenToUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");

        if (token==null){
            throw  new CustomizeException(ErrorEnum.NOT_LOGGED_IN);
        }
        //获取登录用户真正的密码假如数据库查出来的是123456
//        String password = "admin";
        JWTVerifier build = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT verify = build.verify(token);

        Claim claim = verify.getClaim(UID);
        Integer userId = claim.asInt();

        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(UID,userId+"");

        return objectObjectHashMap;

    }





}

