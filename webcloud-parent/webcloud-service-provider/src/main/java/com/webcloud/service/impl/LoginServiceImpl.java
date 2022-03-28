package com.webcloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.webcloud.entity.AccountResult;
import com.webcloud.pojo.Member;
import com.webcloud.service.LoginService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@DubboService(interfaceClass = LoginService.class)
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    public MemberServiceImpl memberService;

    @Override
    public void editPassword(JSONObject jsonObject) {
        String username = (String)jsonObject.get("username");
        String oldPassword = (String)jsonObject.get("oldPassword");
        String newPassword = (String)jsonObject.get("newPassword");
        String flag = (String)jsonObject.get("flag");//标识：手机号或者邮箱
        memberService.editPassword(username,oldPassword,newPassword,flag);

    }

    @Override
    public void registeredAccount(Member member) {
        memberService.add(member);
    }

    @Override
    public AccountResult findPassword(JSONObject jsonObject) {
        String username = (String)jsonObject.get("username");
        String flag = (String)jsonObject.get("flag");//标识：手机号或者邮箱
        AccountResult accountResult = memberService.findPassword(username,flag);
        return accountResult;
    }
}
