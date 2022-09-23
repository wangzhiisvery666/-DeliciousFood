package com.ccut.dachuang.service;

import com.ccut.dachuang.model.pojo.lunBo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author o'k
* @description 针对表【lunBo】的数据库操作Service
* @createDate 2022-09-06 20:40:01
*/
@Service
public interface LunBoService extends IService<lunBo> {

    List<String>  getUrl();
}
