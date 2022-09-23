package com.ccut.dachuang.model.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrentUser {

    private String username;

    private String avatar;

    private String sex;

    private String phoneNumber;

    private String defaultAddress;
}
