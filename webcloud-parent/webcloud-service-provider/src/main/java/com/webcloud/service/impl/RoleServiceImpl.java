package com.webcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webcloud.dao.RoleDao;
import com.webcloud.pojo.Role;
import com.webcloud.service.RoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@DubboService(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleDao,Role> implements RoleService {

    @Autowired
    public RoleDao roleDao;

    @Override
    public Role findByMemberId(Integer id) {
        Role role = roleDao.findByMid(id);
        return role;
    }
}
