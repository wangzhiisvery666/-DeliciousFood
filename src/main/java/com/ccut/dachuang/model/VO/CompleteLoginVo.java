package com.ccut.dachuang.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("完成登陆的展示")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteLoginVo {

    //用户名
    @ApiModelProperty("用户名")
    private String name;

    //头像
    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("token")
    private String token;
}
