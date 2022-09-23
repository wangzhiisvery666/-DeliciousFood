package com.ccut.dachuang.service;

import com.ccut.dachuang.common.CommonResponse;
import com.ccut.dachuang.model.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
* @author o'k
* @description 针对表【menu】的数据库操作Service
* @createDate 2022-09-06 18:57:48
*/
@Service
public interface MenuService extends IService<Menu> {

    CommonResponse<Menu>  getMenu();

}
