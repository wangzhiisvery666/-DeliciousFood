package com.ccut.dachuang.controller;



import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.model.VO.AddressVO;
import com.ccut.dachuang.model.VO.CurrentUser;
import com.ccut.dachuang.model.pojo.Address;
import com.ccut.dachuang.service.AddressService;
import com.ccut.dachuang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/userInfo")
@Api(tags = "修改用户信息模块")
public class UserInfoController {
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @PutMapping("/updateUsername")
    @ApiOperation("修改昵称接口")
    @ApiImplicitParam(name = "newUsername",value = "传入新昵称",required = true,paramType = "path",dataType = "String",defaultValue = "360700")
    public CommonResponse<Boolean> updateUsername(HttpServletRequest request, @RequestParam("newUsername")  String  newUsername  ){

        return   userService.updateUsername(request,newUsername);
    }

    @PostMapping("/addAddress")
    @ApiOperation("添加地址接口")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "newAddress",value = "要添加的地址",required = true,paramType ="query",dataType = "String",defaultValue = "360700"),
            @ApiImplicitParam( name = "permission",value = "是否为默认地址 1 为默认地址0为普通地址",paramType ="query",required = true,dataType = "String",defaultValue = "360700"),
    })
    public CommonResponse<Boolean> addAddress(HttpServletRequest request, String  newAddress,String permission  ){

        return   addressService.addAdress(request,newAddress,permission);
    }


    @DeleteMapping("/deleteAddress")
    @ApiOperation("删除地址接口")
    @ApiImplicitParam(name = "id",value = "要删除的id",required = true,paramType = "path",dataType = "Long",defaultValue = "360700")
    public CommonResponse<Boolean> deleteAddress(HttpServletRequest request, @RequestParam("id")  Long  id  ){

        return   addressService.deleteAdress(id);
    }

    @PutMapping("/updateAddress")
    @ApiOperation("修改地址接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newAddress", value = "新地址", required = true,  dataType = "String", defaultValue = "360700"),
            @ApiImplicitParam(name = "id", value = "传入要要修改地址的id", required = true,  dataType = "Long", defaultValue = "360700"),
            @ApiImplicitParam(name = "permission", value = "是否设为默认地址 1 为默认地址 0为普通地址 ", required = true,  dataType = "String", defaultValue = "360700")
    })
    public CommonResponse<Boolean> updateAddress(HttpServletRequest request, String  newAddress , Long  id , String  permission
    ){

        return  addressService.updateAdress(request,newAddress,id,permission);
    }

    @GetMapping("/getAddress")
    @ApiOperation("获取地址接口")
    public CommonResponse<List<AddressVO>> getAddress(HttpServletRequest request ){
        return   addressService.selectAdress(request);
    }

    @GetMapping("/getCurrentUser")
    @ApiOperation("获取当前的用户信息")
    public  CommonResponse<CurrentUser>  getCurrentUser(HttpServletRequest request){

        return userService.getCurrentUser(request);

    }

}
