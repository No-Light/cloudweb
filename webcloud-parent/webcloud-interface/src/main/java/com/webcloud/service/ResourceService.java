package com.webcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Resource;

import java.util.List;

public interface ResourceService extends IService<Resource> {
    public void add(Resource resource,Integer[] typeIds);

    public void delete(Integer id);

    public void edit(Resource resource,Integer[] typeIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public List<Resource> findAll();
}
