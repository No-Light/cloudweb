package com.webcloud.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.webcloud.entity.AccountResult;
import com.webcloud.pojo.Activity;
import com.webcloud.pojo.Member;

public interface LoginService {

    public void editPassword(JSONObject jsonObject);

    public void registeredAccount(Member member);

    AccountResult findPassword(JSONObject jsonObject);
}
