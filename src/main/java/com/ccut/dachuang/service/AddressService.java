package com.ccut.dachuang.service;

import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.model.VO.AddressVO;
import com.ccut.dachuang.model.pojo.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author o'k
* @description 针对表【address】的数据库操作Service
* @createDate 2022-09-23 17:45:16
*/
@Service
public interface AddressService extends IService<Address> {

    CommonResponse<Boolean> addAdress(HttpServletRequest request, String newAddress, String permission);
    CommonResponse<List<AddressVO>> selectAdress(HttpServletRequest request);
    CommonResponse<Boolean> deleteAdress(Long id);
    CommonResponse<Boolean> updateAdress(HttpServletRequest request,String newAddress,Long id,String permission);

    Address getDefaultAddress(int user_id);
}

