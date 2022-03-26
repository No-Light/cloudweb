package com.webcloud.controller;

import com.webcloud.constant.MessageConstant;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Member;
import com.webcloud.service.MemberService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/member")

public class MemberController {

    @DubboReference
    public MemberService memberService;

    /**
     * 1、校验用户输入的短信验证码是否正确，如果验证码错误则登录失败
     *
     * 2、如果验证码正确，则判断当前用户是否为会员，如果不是会员则自动完成会员注册
     *
     * 3、向客户端写入Cookie，内容为用户手机号
     *
     * 4、将会员信息保存到Redis，使用手机号作为key，保存时长为30分钟
     *
     */

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('ADD_MEMBER')")
    public Result add(@RequestBody Member member, Integer[] typeIds){
        try{
            memberService.add(member,typeIds);
            return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_MEMBER_FAIL);
        }
    }

    @RequestMapping("/findpage")
    @PreAuthorize("hasAuthority('FINDPAGE_MEMBER')")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = memberService.pageQuery(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('EDIT_MEMBER')")
    public Result edit(@RequestBody Member member,Integer[] typeIds){
        try{
            memberService.edit(member,typeIds);
            return new Result(true,MessageConstant.EDIT_MEMBER_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_MEMBER_FAIL);
        }
    }

}
