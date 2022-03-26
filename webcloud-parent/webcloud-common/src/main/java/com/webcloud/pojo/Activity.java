package com.webcloud.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体类有需要就下面添加就行，如果有用到数据库记得在数据库中添好对应属性，且在代码中注意需要用到该属性的地方
 */
@Data
@TableName(value = "c_account")
public class Activity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
//    这里的属性名和数据库中的相同就不一个一个指定了
    private Integer id;

    private String name;

    private String describe;

    private String activityMan;

    private String participationConditions;

    private String price;

    private Integer manNums;
}
