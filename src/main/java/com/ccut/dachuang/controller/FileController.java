package com.ccut.dachuang.controller;

import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Api(tags = "上传文件模块")
@RequestMapping("/uploadFile")
@RestController
@Slf4j
public class FileController {

    @Autowired
    FileService fileService;

    //文件上传
    @PostMapping("/Avatar")
    @ApiOperation("上传头像接口")
    @ApiResponses(
            {
                    @ApiResponse(code = 111, message = "头像应为图片类型"),
                    @ApiResponse(code = 112, message = "头像超过5mb"),
                    @ApiResponse(code = 113, message = "头像上传失败")
            }
    )
    public CommonResponse<String> transAvatar(@RequestPart("img") MultipartFile file, HttpServletRequest request) throws IOException {

        return fileService.sendAvatar(file, request);
    }

}
