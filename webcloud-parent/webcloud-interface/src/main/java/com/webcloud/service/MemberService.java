package com.webcloud.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.pojo.Member;

public interface MemberService extends IService<Member> {
    public void add(Member member,Integer[] typeIds);
    public void edit(Member member,Integer[] typeIds);
    public void delete(Integer id);
    public PageResult pageQuery(QueryPageBean queryPageBean);
}
