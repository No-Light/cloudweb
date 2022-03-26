package com.webcloud.controller;

import com.webcloud.constant.MessageConstant;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Resource;
import com.webcloud.service.ResourceService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @DubboReference
    public ResourceService resourceService;
    /**
     * 在Redis中设立缓存，将条件查询得到的数据存入其中
     * 根据条件的长度动态设立过期时间，避免bigkey长时间存在缓存中影响性能。
     * 注意两个缓存数据库尽量不同
     */
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('ADD_RESOURCE')")
    public Result add(@RequestBody Resource resource,Integer[] typeIds){
        try{
            resourceService.add(resource,typeIds);
            return new Result(true, MessageConstant.ADD_RESOURCE_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_RESOURCE_FAIL);
        }
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE_RESOURCE')")
    public Result delete(Integer id){
        try{
            resourceService.delete(id);
            return new Result(true,MessageConstant.DELETE_RESOURCE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_RESOURCE_FAIL);
        }
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('EDIT_RESOURCE')")
    public Result edit(@RequestBody Resource resource,Integer[] typeIds){
        try{
            resourceService.edit(resource,typeIds);
            return new Result(true, MessageConstant.EDIT_RESOURCE_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_RESOURCE_FAIL);
        }
    }

    @RequestMapping("/findPage")
    @PreAuthorize("hasAuthority('FINDPAGE_RESOURCE')")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = resourceService.pageQuery(queryPageBean);
        return pageResult;
    }


}
