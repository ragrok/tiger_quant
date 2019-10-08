package com.tigerbrokers.quant.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @AUTHOR windli
 * @DATE 2019/7/17 11:02
 * @DESC
 **/
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

    private Integer status;

    private Integer version;


    /**
     * 初始化创建数据
     */
    public void initCreateData() {
        creator = "system";
        createTime = new Date();
        modifier = "system";
        modifyTime = new Date();
        status = 1;
        version = 1;
    }

    /**
     * 更新修改时间
     */
    public void updateData() {
        modifier = "system";
        modifyTime = new Date();
    }
}
