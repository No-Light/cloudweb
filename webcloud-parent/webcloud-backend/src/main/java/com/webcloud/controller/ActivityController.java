package com.webcloud.controller;

import com.webcloud.constant.MessageConstant;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Activity;
import com.webcloud.service.ActivityService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
@RequestMapping(value = "/activity")
public class ActivityController {

    @DubboReference
    public ActivityService activityService;

    @RequestMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
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
        PageResult pageResult = activityService.pageQuery(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/findAll")
    @PreAuthorize("hasAuthority('FINDALL_ACTIVITY')")
    public Result findAll(){
        try{
            List<Activity> result = activityService.findAll();
            return new Result(true,MessageConstant.FIND_ACTIVITY_SUCCESS,result);
        }catch (Exception e){
            return new Result(true,MessageConstant.FIND_ACTIVITY_FAIL);
        }
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public Result delete(Integer id){
        try{
            activityService.delete(id);
            return new Result(true,MessageConstant.DELETE_ACTIVITY_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_ACTIVITY_FAIL);
        }
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public Result edit(@RequestBody Activity activity,Integer[] typeIds){
        try{
            activityService.edit(activity,typeIds);
            return new Result(true,MessageConstant.EDIT_ACTIVITY_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_ACTIVITY_FAIL);
        }
    }


}
