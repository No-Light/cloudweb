package com.webcloud.pojo;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Mapper
public class Permission implements Serializable {
    private Integer id;
    private String name; // 权限名称
    private String keyword; // 权限关键字，用于权限控制
    private String description; // 描述

}
