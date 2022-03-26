package com.webcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webcloud.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

@Mapper
public interface PermissionDao extends BaseMapper<Permission> {

    public Permission findPermissionById(Integer id);
}
