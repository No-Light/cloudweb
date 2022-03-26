package com.webcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webcloud.dao.ResourceDao;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.pojo.Activity;
import com.webcloud.pojo.Resource;
import com.webcloud.service.ResourceService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = ResourceService.class)
@Transactional
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService{

    @Autowired
    public ResourceDao resourceDao;

    @Autowired
    public TemplateEngine templateEngine;

    @Value("${thymeleaf_pagepath}")
    public String pagePath;

    @Override
    public void add(Resource resource, Integer[] typeIds) {
        resourceDao.insert(resource);
        Integer resourceId = resource.getId();
        this.setResourceAndTypes(resourceId,typeIds);
        this.generateTemplatePage(resource);
    }

    public int findByIdCount(Integer id){
        return resourceDao.selectCount(new QueryWrapper<Resource>().eq("id",id));
    }

    @Override
    public void delete(Integer id) {
        if (resourceDao.selectById(id) != null){
            resourceDao.deleteAssoication(id);
            resourceDao.deleteById(id);
        }
    }

    @Override
    public void edit(Resource resource, Integer[] typeIds) {
        resourceDao.update(resource, new UpdateWrapper<Resource>().eq("id",resource.getId()));
        Integer resourceId = resource.getId();
        resourceDao.deleteAssoication(resourceId);
        this.setResourceAndTypes(resourceId,typeIds);
        this.generateTemplatePage(resource);
    }

    //页面静态化可能并不完善
    public void generateTemplatePage(Resource resource){
        Context context = new Context();
        Map<String,Object> savData = new HashMap<>();
        savData.put("resource",resource);
        context.setVariables(savData);

        File file = new File(pagePath+"/resource_detail_"+resource.getId()+".html");
        Writer out = null;
        try{
            out = new FileWriter(file);
            templateEngine.process("resource_template",context,out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        IPage<Resource> iPage = new Page<>(currentPage,pageSize);
        IPage<Resource> result = resourceDao.selectPage(iPage, new QueryWrapper<Resource>().like("name", queryString).or()
                .like("describe", queryPageBean).or()
                .like("resourceMan", queryPageBean).or()
                .like("url", queryPageBean));
        return new PageResult(result.getTotal(),result.getRecords());
    }

    @Override
    public List<Resource> findAll() {
        List<Resource> resources = resourceDao.selectList(null);
        return resources;
    }

    private void setResourceAndTypes(Integer resourceId,Integer[] typeIds){
        if (typeIds != null && typeIds.length > 0){
            for (Integer typeId : typeIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("rid",resourceId);
                map.put("tid",typeId);
                resourceDao.setResourceAndTypes(map);
            }
        }
    }

}
