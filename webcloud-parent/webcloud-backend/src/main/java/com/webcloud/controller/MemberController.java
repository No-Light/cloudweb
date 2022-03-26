package com.webcloud.controller;

import com.webcloud.constant.MessageConstant;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Member;
import com.webcloud.service.MemberService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/member")

public class MemberController {

    @DubboReference
    public MemberService memberService;

    @RequestMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
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

    @RequestMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public Result delete(Integer id){
        try{
            memberService.delete(id);
            return new Result(true,MessageConstant.DELETE_MEMBER_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_MEMBER_FAIL);
        }
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
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
