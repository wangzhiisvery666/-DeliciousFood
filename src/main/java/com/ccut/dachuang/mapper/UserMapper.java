package com.ccut.dachuang.mapper;

import com.ccut.dachuang.model.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author o'k
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-09-04 17:12:18
* @Entity com.ccut.dachuang.model.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User userIsExist(@Param("username") String username,@Param("password") String  password );

    int  updateAvatarByInt(@Param("id") Integer id ,@Param("newAvatar") String avatarUrl);
    int  updateUsernameById(@Param("id") Integer id ,@Param("username") String username);
}




