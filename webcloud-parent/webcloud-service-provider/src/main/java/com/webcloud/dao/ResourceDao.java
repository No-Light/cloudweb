package com.webcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webcloud.pojo.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ResourceDao extends BaseMapper<Resource> {

    public void setResourceAndTypes(Map<String, Integer> map);

    public void deleteAssoication(Integer id);
}
