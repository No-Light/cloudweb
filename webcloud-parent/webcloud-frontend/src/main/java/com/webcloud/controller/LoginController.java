package com.webcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.webcloud.constant.MessageConstant;
import com.webcloud.entity.AccountResult;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Member;
import com.webcloud.service.LoginService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {

    @DubboReference
    public LoginService loginService;

    @RequestMapping("/registeredAccount")
    public Result registeredAccount(@RequestBody Member member){
        try{
            loginService.registeredAccount(member);
            return new Result(true,MessageConstant.REGISTERED_ACCOUNT_SUCCESS,member);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.REGISTERED_ACCOUNT_FAIL);
        }
    }

    @RequestMapping("/findPassword")
    public Result findPassword(@RequestBody JSONObject jsonObject){
        try{
            AccountResult accountResult = loginService.findPassword(jsonObject);
            return new Result(true,MessageConstant.FIND_PASSWORD_SUCCESS, accountResult);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.FIND_PASSWORD_FAIL);
        }
    }

//    别忘了把session设置为空
    @RequestMapping("/editPassword")
    @PreAuthorize("hasRole('MEMBER')")
    public Result editPassword(@RequestBody JSONObject jsonObject){
        try{
            loginService.editPassword(jsonObject);
            return new Result(true,MessageConstant.EDIT_PASSWORD_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_PASSWORD_FAIL);
        }
    }


}
