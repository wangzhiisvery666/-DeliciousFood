package com.ccut.dachuang.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccut.dachuang.model.pojo.BaseArea;
import com.ccut.dachuang.model.pojo.BaseCity;
import com.ccut.dachuang.model.pojo.BaseProvince;
import com.ccut.dachuang.service.BaseAreaService;
import com.ccut.dachuang.service.BaseCityService;
import com.ccut.dachuang.service.BaseProvinceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "省市区联动")
@RestController
@RequestMapping("/area")
@Slf4j
public class AreaController {

    @Autowired
    private BaseProvinceService provinceService;
    @Autowired
    private BaseAreaService areaService;
    @Autowired
    private BaseCityService cityService;
    /**
     * 获取所有的省份信息
     * @return :返回所有城市
     */
    @ApiOperation(value = "获取所有的省份信息")
    @GetMapping("/getProvince")
    @ResponseBody
    public List<BaseProvince> getProvince(){
        List<BaseProvince> list = provinceService.list();
        log.info("成功获得省信息");
        return list;
    }

    /**
     * 根据省份id获取城市信息
     * @param provincecode :省的编码
     * @return ：返回所有城市
     */
    @ApiOperation(value = "根据省份id获取城市信息")
    @ApiImplicitParam(name = "provincecode",value = "传入的省份id",required =true,paramType = "path",dataType = "String",defaultValue = "360000")
    @GetMapping("/getCity/{provincecode}")
    public List<BaseCity> getCity(@PathVariable String provincecode){
        List<BaseCity> list = cityService.list(new QueryWrapper<BaseCity>().eq("provincecode", provincecode));
        return list;
    }

    /**
     * 根据城市id获取县区信息
     * @param citycode :城市编码
     * @return :返回所有县区
     */
    @ApiOperation(value = "根据城市id获取县区信息")
    @ApiImplicitParam(name = "citycode",value = "传入的城市id",required = true,paramType = "path",dataType = "String",defaultValue = "360700")
    @GetMapping("/getArea/{citycode}")
    public List<BaseArea> getArea(@PathVariable String citycode){
        List<BaseArea> list = areaService.list(new QueryWrapper<BaseArea>().eq("citycode", citycode));
        return list;
    }

}

