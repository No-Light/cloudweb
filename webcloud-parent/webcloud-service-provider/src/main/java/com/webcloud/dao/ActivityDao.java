package com.webcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webcloud.pojo.Activity;
import com.webcloud.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ActivityDao extends BaseMapper<Activity> {

    public void setActivityAndTypes(Map<String, Integer> map);

    public void deleteAssoication(Integer id);
}
