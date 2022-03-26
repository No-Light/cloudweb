package com.webcloud.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "c_resource")
public class Resource implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String  describe;

    private String resourceMan;

    private String url;

}
