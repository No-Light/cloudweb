package com.webcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webcloud.constant.MessageConstant;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Resource;
import com.webcloud.service.ResourceService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @DubboReference
    public ResourceService resourceService;

    @Autowired
    public JedisPool jedisPool;

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
        try{
            if(queryPageBean.getQueryString()!=""&&jedisPool.getResource().get(queryPageBean.toString())!=null){
                String jsonstr = jedisPool.getResource().get(queryPageBean.toString());
                List<Resource> resources = JSONObject.parseArray(jsonstr, Resource.class);
                return new PageResult((long) resources.size(),resources);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        PageResult pageResult = resourceService.pageQuery(queryPageBean);
        try {
            int timeout = 864000/queryPageBean.toString().length();
            jedisPool.getResource().setex(queryPageBean.toString(),timeout,JSON.toJSONString(pageResult.getRows()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageResult;
    }


}
