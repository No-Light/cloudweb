package com.webcloud.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;

/**
 * 之后写的时候记得不用写社团成员离校之后删除账号信息了。
 * 不然上传的资源不好查找信息了。
 */
@Data
@TableName(value = "c_member")
public class Member implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    //    这里的属性名和数据库中的相同就不一个一个指定了
    private Integer id;

    private String name;

    private String sex;

    private Date birthday;

    private Date admissionTime;

    private Integer integral;

    private String username;

    private String password;

    private String telPhone;

    private String email;

    private String role;
//  需要时拿memberid去查表。

}
