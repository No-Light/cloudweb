package com.webcloud.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Activity;

import java.util.List;

public interface ActivityService extends IService<Activity> {
    public void add(Activity activity,Integer[] typeIds);
    public void edit(Activity activity,Integer[] typeIds);
    public void delete(Integer id);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public List<Activity> findAll();
    public void signup(JSONObject jsonObject);
    public void cancelregistration(JSONObject jsonObject);
}
