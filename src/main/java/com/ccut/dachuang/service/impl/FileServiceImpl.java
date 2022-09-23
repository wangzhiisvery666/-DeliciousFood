package com.ccut.dachuang.service.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ccut.dachuang.Exception.CustomizeException;
import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.common.ErrorEnum;
import com.ccut.dachuang.mapper.UserMapper;
import com.ccut.dachuang.model.pojo.User;
import com.ccut.dachuang.service.FileService;
import com.ccut.dachuang.service.UserService;
import com.ccut.dachuang.utils.CommonUtils;
import com.ccut.dachuang.utils.JwtUtil;
import com.ccut.dachuang.utils.MultipartFileToFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.util.Date;


@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${oos.accessKey}")
    private String accessKey;

    @Value("${oos.secretKey}")
    private String secretKey;

    @Value("${oos.domain}")
    private String domain;


    @Value("${oos.bucketname}")
    private String bucketname;

    @Value("${oos.endpoint}")
    private String endpoint;

    @Autowired
    UserMapper userMapper;


    @Override
    public CommonResponse<String> sendAvatar(MultipartFile avatar, HttpServletRequest request) {


        try {
        //超过5mb
        if (avatar.getSize()>5242880){
            throw  new CustomizeException(ErrorEnum.AVATAR_OVER_5MB);
        }

        String avatarName = avatar.getOriginalFilename();
        System.out.println(avatarName);


        if (!(avatarName.endsWith(".jpg") || avatarName.endsWith(".jpeg") || avatarName.endsWith(".png"))) {
                throw  new CustomizeException(ErrorEnum.WRONG_FILE_TYPE);
            }


        // TODO: 2022/9/21 这里有问题需要结合 token : request

            String token = request.getHeader("token");

            log.info("token为:{}", token);
            DecodedJWT decodedJWT = JwtUtil.decodeToken(token);

            //从
            Claim id = decodedJWT.getClaim(JwtUtil.UID);

            Integer integer = id.asInt();

            //用md5加密id来当头像照片
            String PName=CommonUtils.md5(integer + "");;
        //存储路径
        String storagePath="img/avatar/";
        //头像名
        avatarName= PName+ avatarName.substring(avatarName.lastIndexOf("."));

        //转换为File类型  法一
        File file = MultipartFileToFile.toFileTwo(avatar);
        //文件转换      法二
//        File file = MultipartFileToFile.toFileOne(avatar);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);
        //上传文件
        PutObjectResult putObjectResult = ossClient.putObject(new PutObjectRequest(bucketname, storagePath+avatarName,file));

//      URL url = ossClient.generatePresignedUrl(bucketname, storagePath + avatarName, new Date(System.currentTimeMillis() + 1010000L));
//      log.info(url.toString());
        //删除临时文件
        file.delete();

//      MultipartFileToFile.delteTempFile(file);

        String avatarUrl=domain+storagePath+avatarName;

         if (putObjectResult==null){
                throw  new CustomizeException(ErrorEnum.AVATAR_UPLOAD_FAILED);
         }
         log.info("头像上传成功地址为: {}" ,avatarUrl);

         //将数据库里用户头像更新
        userMapper.updateAvatarByInt(integer,avatarUrl);

        return new CommonResponse<>("成功返回头像url",avatarUrl,"200") ;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
