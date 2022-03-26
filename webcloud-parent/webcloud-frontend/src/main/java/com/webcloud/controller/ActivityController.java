package com.webcloud.controller;

import com.webcloud.constant.MessageConstant;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Activity;
import com.webcloud.service.ActivityService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/activity")
public class ActivityController {

    @DubboReference
    public ActivityService activityService;

    /**
     * 活动相较其他的查询应该会更加频繁，所以尝试着将之存入redis就是下一步的目标。
     * 在Redis中设立缓存，将条件查询得到的数据存入其中
     * 根据条件的长度动态设立过期时间，避免bigkey长时间存在缓存中影响性能。
     */

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
        PageResult pageResult = activityService.pageQuery(queryPageBean);
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


}
