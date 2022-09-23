package com.ccut.dachuang.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ccut.dachuang.Exception.CustomizeException;
import com.ccut.dachuang.common.ErrorEnum;
import com.ccut.dachuang.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: token 拦截器
 */
@Slf4j
@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        log.info("request请求地址path:[{}] uri:[{}]", request.getServletPath(),request.getRequestURI());
        String token = request.getHeader("token");

        log.info("token:" + token);

        if ("".equals(token)||token==null) {
            log.error("发生错误：token为空");
            throw new CustomizeException(ErrorEnum.NOT_LOGGED_IN);
        }

        try {
            JwtUtil.decodeToken(token);
        } catch (SignatureVerificationException e) {
            log.error("=====================无效签名=========");
            throw new CustomizeException(ErrorEnum.TOKEN_ERROR);
        } catch (TokenExpiredException e) {
            log.error("====================token过期========");
            throw new CustomizeException(ErrorEnum.SESSION_EXPIRED);
        } catch (AlgorithmMismatchException e) {
            log.error("===========token算法不一致============");
            throw new CustomizeException(ErrorEnum.TOKEN_ERROR);
        } catch (Exception e) {
            log.error("================token无效===========");
            throw new CustomizeException(ErrorEnum.TOKEN_ERROR);
        }
        return true;
    }
    /**
     * 根据token获取用户ID
     * @param userToken
     * @return
     */
    private Long getUserId(String userToken){
        Long userId = null;
        return userId;
    }

    /**
     * 校验用户访问权限
     * @param userId
     * @param requestURI
     * @return
     */
    private boolean checkAuth(Long userId,String requestURI){
        return true;
    }

}

