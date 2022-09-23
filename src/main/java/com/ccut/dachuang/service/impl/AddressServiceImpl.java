package com.ccut.dachuang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccut.dachuang.Exception.CustomizeException;
import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.common.ErrorEnum;
import com.ccut.dachuang.model.VO.AddressVO;
import com.ccut.dachuang.model.pojo.Address;
import com.ccut.dachuang.service.AddressService;
import com.ccut.dachuang.mapper.AddressMapper;
import com.ccut.dachuang.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* @author o'k
* @description 针对表【address】的数据库操作Service实现
* @createDate 2022-09-23 17:45:16
*/
@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    AddressService addressService;
    @Autowired
    AddressServiceImpl addressService1;


    /**
     * 添加地址
     * @param address ：地址信息
     * @param permission ：是否设为默认地址
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResponse<Boolean> addAdress(HttpServletRequest request, String address,String permission) {

        //解析token获取  id
        HashMap<String, String> decodeTokenToUserInfo = JwtUtil.getDecodeTokenToUserInfo(request);
        int uid =Integer.parseInt(decodeTokenToUserInfo.get(JwtUtil.UID));

        QueryWrapper<Address> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("user_id",uid);

        //设置对象准备查询
        Address addressEntity = new Address();
        addressEntity.setLocation(address);
        addressEntity.setUserId(uid);

        long count = addressService.count(objectQueryWrapper);
        //如果只有这一个地址则把该地址设为默认地址
        if (count==0){
            addressEntity.setPermission("1");
        }
        //如果不是第一个地址 并且 把这该地址设为默认地址
        if (count!=0&&permission.equals("1")){
            //把原先的默认地址改为普通地址
            addressMapper.updatePermissionByUserId(uid);
            addressEntity.setPermission("1");
        }
        //如果不是第一个地址 并且 不把该地址 设为默认地址
        if (count!=0&&permission.equals("0")){
            addressEntity.setPermission("0");
        }

        boolean save = addressService.save(addressEntity);
        if (!save){
            throw  new CustomizeException(ErrorEnum.ADDRESS_ADD_FAILED);
        }
        log.info("添加地址成功");
        return new CommonResponse<>("地址添加成功",true,"200");

    }

    /**
     * 查询所有的地址
     * @param request
     * @return
     */
    @Override
    public CommonResponse<List<AddressVO>> selectAdress(HttpServletRequest request) {
        HashMap<String, String> decodeTokenToUserInfo = JwtUtil.getDecodeTokenToUserInfo(request);
        String put = decodeTokenToUserInfo.get(JwtUtil.UID);
        int id = Integer.parseInt(put);

        QueryWrapper<Address> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("user_id",id);

        List<Address> addresses = addressMapper.selectList(objectQueryWrapper);
        ArrayList<AddressVO> objects = new ArrayList<>();

        for (Address address :addresses) {
            AddressVO addressVO = new AddressVO();
            addressVO.setAddressId(address.getId());
            addressVO.setLocation(address.getLocation());
            addressVO.setPermission(address.getPermission());
            objects.add(addressVO);
        }


        log.info("返回所有地址");
        return new CommonResponse<>("返回所有地址",objects,"200");
    }

    /**
     * 删除地址
     * @param id
     * @return
     */
    @Override
    public CommonResponse<Boolean> deleteAdress(Long id) {

        try{
            addressMapper.deleteById(id);
        }catch (Exception e){
            throw  new CustomizeException(ErrorEnum.ADDRESS_DELETE_FAILED);
        }

        log.info("删除地址成功");
        return new CommonResponse<>("地址删除成功",true,"200");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResponse<Boolean> updateAdress(HttpServletRequest request, String newAddress, Long id,String permission) {
        String s = JwtUtil.getDecodeTokenToUserInfo(request).get(JwtUtil.UID);

        int uid= Integer.parseInt(s);
        Address address = new Address();
        if (permission.equals("1")){
            addressMapper.updatePermissionByUserId(uid);
            address.setPermission("1");
        }else {
            address.setPermission("0");
        }


        address.setId(id);
        address.setLocation(newAddress);

        address.setUserId(uid);

        int i = addressMapper.updateById(address);
        if (i<0){
            throw new CustomizeException(ErrorEnum.FAIL_TO_EDIT);
        }
        log.info("修改地址成功");
        return new CommonResponse<>("地址修改成功",true,"200");
    }

    @Override
    public Address getDefaultAddress(int id) {

        QueryWrapper<Address> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq("user_id",id);
        QueryWrapper.eq("permission",'1');

        return  addressMapper.selectOne(QueryWrapper);
    }


}




