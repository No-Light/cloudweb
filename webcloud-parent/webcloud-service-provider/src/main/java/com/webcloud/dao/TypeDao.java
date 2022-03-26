package com.webcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webcloud.pojo.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeDao extends BaseMapper<Type> {
    public List<Type> findTypesByActivityId(Integer id);

    public List<Type> findTypesByResourceId(Integer id);
}
