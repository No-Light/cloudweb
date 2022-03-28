package com.webcloud.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webcloud.pojo.Member;
import com.webcloud.pojo.Permission;
import com.webcloud.pojo.Role;
import com.webcloud.service.MemberService;
import com.webcloud.service.RoleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MemberDetailsSecurity implements UserDetailsService {

    @DubboReference
    public MemberService memberService;

    @DubboReference
    public RoleService roleService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.getOne(new QueryWrapper<Member>().eq("username",username));
        if(member == null){throw new UsernameNotFoundException(username + "not found");}

        List<GrantedAuthority> list = new ArrayList<>();
        Role role = roleService.findByMemberId(member.getId());
        list.add(new SimpleGrantedAuthority(role.getKeyword()));
        Set<Permission> permissions = role.getPermissions();
        for(Permission permission : permissions){
            list.add(new SimpleGrantedAuthority(permission.getKeyword()));
        }
        User securityUser = new User(username,member.getPassword(),list);
        return securityUser;
    }
}