package com.itheima.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * 角色描述表
 *
 * @author wangweili
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TRole implements Serializable {

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 角色描述
     */
    private String description;
}
