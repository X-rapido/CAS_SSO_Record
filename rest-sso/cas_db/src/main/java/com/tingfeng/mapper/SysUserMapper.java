package com.tingfeng.mapper;

import com.tingfeng.domain.SysUser;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper {

    @Select("select * from sys_user where username=#{username}")
    SysUser getSysUser(String username);

}
