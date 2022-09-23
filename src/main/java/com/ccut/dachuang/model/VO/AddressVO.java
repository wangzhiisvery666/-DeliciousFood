package com.ccut.dachuang.model.VO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("地址")
public class AddressVO {

    @ApiModelProperty("地址id")
    private Long addressId;
    @ApiModelProperty("地址内容")
    private String location;
    @ApiModelProperty("地址是否为默认 1 为是默认 0 为不是默认")
    private String permission;
}
