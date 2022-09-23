package com.ccut.dachuang.config;


import com.ccut.dachuang.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/**
 * @Description: JWT拦截器
 */
@Configuration
@EnableWebMvc
public class InterceptorConfig implements WebMvcConfigurer {


    @Autowired
    JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                //排除接口
                //不要加api
                //拦截的路径
                .addPathPatterns("/**")
                .excludePathPatterns("/LunBo/**", "/login/verify", "/register/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v3/**" ,"/swagger-ui/**");

    }

}

