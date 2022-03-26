package com.webcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webcloud.pojo.Resource;
import com.webcloud.pojo.Role;

public interface RoleService extends IService<Role> {

    public Role findByMemberId(Integer id);
}
