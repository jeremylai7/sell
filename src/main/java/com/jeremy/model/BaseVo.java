package com.jeremy.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Auther: laizc
 * @Date: 2020/3/26 22:38
 * @Description:
 */
@Getter
@Setter
public class BaseVo {
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
