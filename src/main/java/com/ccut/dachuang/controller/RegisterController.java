package com.ccut.dachuang.controller;

import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.model.VO.RegisterVo;
import com.ccut.dachuang.service.impl.UserServiceImpl;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@Api(tags = {"注册模块接口"})
public class RegisterController {

    @Autowired
    UserServiceImpl userService;

    @ApiOperation("测试接口 可以先调用试试")
    @GetMapping("/hh")
    public String test(){
        return "success!";
    }

    @ApiOperation("注册模块")
    @PostMapping("/add")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "user",value ="Register",required = true)
    )
    @ApiResponses({

            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 100, message = "参数为空"),
            @ApiResponse(code = 101, message = "用户名长度小于4"),
            @ApiResponse(code = 102, message = "用户名已存在"),
            @ApiResponse(code = 103, message = "该手机已经绑定用户"),
            @ApiResponse(code = 104, message = "密码长度小于6"),
            @ApiResponse(code = 105, message = "用户名包含敏感词"),
            @ApiResponse(code = 106, message = "数据库插入失败"),

    })
    public CommonResponse<Boolean> register(@RequestBody RegisterVo user){
        return userService.addUser(user);
    }


    @ApiOperation("获取验证码接口")
    @GetMapping("/getVerificationCode")
    @ApiResponses({

            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 100, message = "参数为空"),
            @ApiResponse(code = 101, message = "用户名长度小于4"),
            @ApiResponse(code = 102, message = "用户名已存在"),
            @ApiResponse(code = 103, message = "该手机已经绑定用户"),
            @ApiResponse(code = 104, message = "密码长度小于6"),
            @ApiResponse(code = 105, message = "用户名包含敏感词"),
            @ApiResponse(code = 106, message = "数据库插入失败"),

    })
    public CommonResponse<String> getVerificationCode( String username,String password , String phoneNumber ){

        return userService.getVerificationCode(username,password,phoneNumber);
    }
}
