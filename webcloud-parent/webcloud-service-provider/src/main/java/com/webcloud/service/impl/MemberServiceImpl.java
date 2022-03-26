package com.webcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webcloud.dao.MemberDao;
import com.webcloud.dao.MenuDao;
import com.webcloud.dao.PermissionDao;
import com.webcloud.dao.RoleDao;
import com.webcloud.entity.PageResult;
import com.webcloud.entity.QueryPageBean;
import com.webcloud.pojo.Member;
import com.webcloud.service.MemberService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@DubboService(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl extends ServiceImpl<MemberDao, Member> implements MemberService {

    @Autowired
    public MemberDao memberDao;
    @Autowired
    public RoleDao roleDao;
    /**
     * 权限和菜单定死了，所以插入操作只需要将member和role绑定就行
     */
    @Autowired
    public PermissionDao permissionDao;
    @Autowired
    public MenuDao menuDao;

    @Override
    public void add(Member member,Integer[] typeIds) {
        String role = member.getRole();
        Integer roleId = role == null ? roleDao.findIdByRole("成员") : roleDao.findIdByRole(role);
        memberDao.insert(member);
        this.setMemberAndRole(member.getId(),roleId);
    }

    @Override
    public void edit(Member member,Integer[] typeIds) {
        String role = member.getRole();
        Integer roleId = roleDao.findIdByRole(role);
        memberDao.updateById(member);
        this.setMemberAndRole(member.getId(),roleId);
    }

    @Override
    public void delete(Integer id) {
        if (memberDao.selectById(id) != null){
            memberDao.deleteAssoication(id);
            memberDao.deleteById(id);
        }
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        IPage<Member> iPage = new Page<>(currentPage,pageSize);
        IPage<Member> result = memberDao.selectPage(iPage,new QueryWrapper<Member>()
                .like("name",queryString).or()
                .like("username",queryPageBean).or());
        return new PageResult(result.getTotal(),result.getRecords());
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = memberDao.selectList(null);
        return members;
    }

    private void setMemberAndRole(Integer memberId,Integer roleId){
        Map<String, Integer> map = new HashMap<>();
        map.put("mid", memberId);
        map.put("rid", roleId);
        memberDao.setMemberAndRole(map);
    }

}
