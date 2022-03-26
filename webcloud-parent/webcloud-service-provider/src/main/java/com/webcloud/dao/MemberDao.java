package com.webcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.pojo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberDao extends BaseMapper<Member> {

    public void setMemberAndRole(Map<String, Integer> map);

    public void deleteAssoication(Integer id);

}
