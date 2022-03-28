package com.webcloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.webcloud.constant.MessageConstant;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Activity;
import com.webcloud.pojo.Resource;
import com.webcloud.service.ActivityService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
@RequestMapping(value = "/activity")
public class ActivityController {

    @DubboReference
    public ActivityService activityService;

    @Autowired
    public JedisPool jedisPool;

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('ADD_ACTIVITY')")
    public Result add(@RequestBody Activity activity,Integer[] typeIds){
        try{
            activityService.add(activity,typeIds);
            return new Result(true, MessageConstant.ADD_ACTIVITY_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_ACTIVITY_FAIL);
        }
    }

    @RequestMapping("/findpage")
    @PreAuthorize("hasAuthority('FINDPAGE_ACTIVITY')")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        try{
            if(queryPageBean.getQueryString()!=""&&jedisPool.getResource().get(queryPageBean.toString())!=null){
                String jsonstr = jedisPool.getResource().get(queryPageBean.toString());
                List<Activity> activities = JSONObject.parseArray(jsonstr, Activity.class);
                return new PageResult((long) activities.size(),activities);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        PageResult pageResult = activityService.pageQuery(queryPageBean);
        try {
            int timeout = 864000/queryPageBean.toString().length();
            jedisPool.getResource().setex(queryPageBean.toString(),timeout, JSON.toJSONString(pageResult.getRows()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageResult;
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('EDIT_ACTIVITY')")
    public Result edit(@RequestBody Activity activity,Integer[] typeIds){
        try{
            activityService.edit(activity,typeIds);
            return new Result(true,MessageConstant.EDIT_ACTIVITY_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_ACTIVITY_FAIL);
        }
    }

    @RequestMapping("/signup")
    public Result signup(@RequestBody JSONObject jsonObject){
        try{
            activityService.signup(jsonObject);
            return new Result(true,MessageConstant.SIGN_ACTIVITY_SUCCESS);
        }catch (Exception e){
            return new Result(false,MessageConstant.SIGN_ACTIVITY_FAIL);
        }
    }

    @RequestMapping("/cancelregistration")
    public Result cancelregistration(@RequestBody JSONObject jsonObject){
        try{
            activityService.cancelregistration(jsonObject);
            return new Result(true,MessageConstant.CANCEL_RGISTRATION_SUCCESS);
        }catch (Exception e){
            return new Result(false,MessageConstant.CANCEL_RGISTRATION_FAIL);
        }
    }

}
