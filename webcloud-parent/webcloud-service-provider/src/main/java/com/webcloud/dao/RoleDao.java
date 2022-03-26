package com.webcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webcloud.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao extends BaseMapper<Role> {
    public Integer findIdByRole(String role);

    public Role findByMid(Integer id);
}
