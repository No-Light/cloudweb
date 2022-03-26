package com.webcloud.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.webcloud.constant.MessageConstant;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Type;
import com.webcloud.service.TypeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/type")
public class TypeController {

    @DubboReference
    public TypeService typeService;

    @RequestMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(@RequestBody Type type){
        try{
            typeService.add(type);
            return new Result(true, MessageConstant.ADD_TYPE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_TYPE_FAIL);
        }
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public Result delete(Integer id){
        try{
            typeService.delete(id);
            return new Result(true,MessageConstant.DELETE_TYPE_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_TYPE_FAIL);
        }
    }

    @RequestMapping("/findpage")
    @PreAuthorize("hasAuthority('FINDPAGE_TYPE')")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = typeService.pageQuery(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/findTypesByActivityId")
    @PreAuthorize("hasAuthority('FINDPAGE_TYPE')")
    public Result findTypesByActivityId(Integer id){
        List<Type> typeByActivityId = typeService.findTypesByActivityId(id);
        return new Result(true,MessageConstant.ENTRY,typeByActivityId);
    }

    @RequestMapping("/findTypesByResourceId")
    @PreAuthorize("hasAuthority('FINDPAGE_TYPE')")
    public Result findTypesByResourceId(Integer id){
        List<Type> typeByResourceId = typeService.findTypesByResourceId(id);
        return new Result(true,MessageConstant.ENTRY,typeByResourceId);
    }

}
