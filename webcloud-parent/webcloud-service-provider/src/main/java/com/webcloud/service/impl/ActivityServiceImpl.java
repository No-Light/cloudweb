package com.webcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webcloud.dao.ActivityDao;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.pojo.Activity;
import com.webcloud.service.ActivityService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@DubboService(interfaceClass = ActivityService.class)
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl extends ServiceImpl<ActivityDao, Activity> implements ActivityService {

    @Autowired
    public ActivityDao activityDao;

    @Override
    public void add(Activity activity, Integer[] typeIds) {
        activityDao.insert(activity);
        Integer activityId = activity.getId();
        this.setActivityAndTypes(activityId,typeIds);
    }

    @Override
    public void edit(Activity activity, Integer[] typeIds) {
        activityDao.update(activity, new UpdateWrapper<Activity>().eq("id",activity.getId()));
        Integer activityId = activity.getId();
        activityDao.deleteAssoication(activityId);
        this.setActivityAndTypes(activityId,typeIds);
    }

    public int findByIdCount(Integer id){
        return activityDao.selectCount(new QueryWrapper<Activity>().eq("id",id));
    }

    @Override
    public void delete(Integer id) {
        if (activityDao.selectById(id) != null){
            activityDao.deleteAssoication(id);
            activityDao.deleteById(id);
        }
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        IPage<Activity> iPage = new Page<>(currentPage,pageSize);
        IPage<Activity> result = activityDao.selectPage(iPage, new QueryWrapper<Activity>().like("name", queryString).or()
                .like("describe", queryPageBean).or()
                .like("activityMan", queryPageBean).or()
                .like("participationConditions", queryPageBean));
        return new PageResult(result.getTotal(),result.getRecords());
    }

    private void setActivityAndTypes(Integer activityId,Integer[] typeIds){
        if (typeIds != null && typeIds.length > 0){
            for (Integer typeId : typeIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("aid",activityId);
                map.put("tid",typeId);
                activityDao.setActivityAndTypes(map);
            }
        }
    }

}
