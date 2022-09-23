package com.ccut.dachuang.controller;

import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.model.VO.CompleteLoginVo;
import com.ccut.dachuang.service.impl.UserServiceImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/login")
@Api(tags = "登陆模块")
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/verify")
    @ApiResponses({

            @ApiResponse(code = 108, message = "用户名为空"),
            @ApiResponse(code = 109, message = "密码为空"),
            @ApiResponse(code = 110, message = "用户名或密码错误"),
    })
    @ApiOperation("登陆接口")
    public CommonResponse<CompleteLoginVo>  doLogin( String username,String password, HttpServletRequest request){

        return  userService.login(username, password, request);
    }


    @GetMapping("/out")
    @ApiResponses({
        @ApiResponse(code = 200, message = "成功退出"),
    })
    @ApiOperation("退出接口")
    public CommonResponse<Boolean>  logOut(HttpServletRequest request){
         return userService.logOut(request);
    }

}
