package com.webcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.entity.Result;
import com.webcloud.pojo.Type;

import java.util.List;

public interface TypeService extends IService<Type> {
    public void add(Type type);

    public void delete(Integer id);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public List<Type> findTypesByActivityId(Integer id);

    public List<Type> findTypesByResourceId(Integer id);

    public List<Type> findAll();
}
