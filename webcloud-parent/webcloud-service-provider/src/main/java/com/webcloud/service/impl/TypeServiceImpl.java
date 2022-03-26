package com.webcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webcloud.dao.TypeDao;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.pojo.Type;
import com.webcloud.service.TypeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DubboService(interfaceClass = TypeService.class)
@Transactional(rollbackFor = Exception.class)
public class TypeServiceImpl extends ServiceImpl<TypeDao, Type> implements TypeService {

    @Autowired
    public TypeDao typeDao;

    @Override
    public void add(Type type) {
        typeDao.insert(type);
    }

    public int findByIdCount(Integer id){
        return typeDao.selectCount(new QueryWrapper<Type>().eq("id",id));
    }

    @Override
    public void delete(Integer id) {
        if (findByIdCount(id) == 1){
            typeDao.deleteById(id);
        }
        new RuntimeException();
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        IPage<Type> iPage = new Page<>(currentPage,pageSize);
        IPage<Type> result = typeDao.selectPage(iPage, new QueryWrapper<Type>().like("type", queryString));
        return new PageResult(result.getTotal(),result.getRecords());
    }

    @Override
    public List<Type> findTypesByActivityId(Integer id) {
        List<Type> result = typeDao.findTypesByActivityId(id);
        return result;
    }

    @Override
    public List<Type> findTypesByResourceId(Integer id) {
        List<Type> result = typeDao.findTypesByResourceId(id);
        return result;
    }

    @Override
    public List<Type> findAll() {
        List<Type> types = typeDao.selectList(null);
        return types;
    }
}
