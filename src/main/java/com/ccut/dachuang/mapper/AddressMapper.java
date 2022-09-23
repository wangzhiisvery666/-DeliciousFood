package com.ccut.dachuang.mapper;

import com.ccut.dachuang.model.pojo.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author o'k
* @description 针对表【address】的数据库操作Mapper
* @createDate 2022-09-23 17:45:16
* @Entity com.ccut.dachuang.model.pojo.Address
*/
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

    int updatePermissionByUserId(@Param("user_id") Integer id);
}




