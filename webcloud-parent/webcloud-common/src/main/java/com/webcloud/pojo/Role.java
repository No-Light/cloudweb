package com.webcloud.pojo;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Mapper
public class Role implements Serializable {
    private Integer id;
    private String role; // 角色名称
    private String keyword; // 角色关键字，用于权限控制
    private String description; // 描述
//    在这个系统中，一个member只对应一个role，需要时可以用memberid去查，对数据库压力会比较大，换取开发效率
    private Set<Permission> permissions = new HashSet<Permission>(0);
//    private LinkedHashSet<Menu> menus = new LinkedHashSet<Menu>(0);
}
