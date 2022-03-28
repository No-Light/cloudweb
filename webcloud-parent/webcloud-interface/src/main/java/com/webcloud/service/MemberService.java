package com.webcloud.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.webcloud.entity.AccountResult;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.pojo.Member;

import java.util.List;

public interface MemberService extends IService<Member> {
    public void add(Member member);
    public void edit(Member member);
    public void delete(Integer id);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public List<Member> findAll();
    public void editPassword(String username, String oldPassword, String newPassword,String flag);
    public AccountResult findPassword(String username, String flag);
}
