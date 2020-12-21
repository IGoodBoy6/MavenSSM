package com.itheima.pojo;

import lombok.*;

/**
 * 权限表的pojo类
 *
 * @author wangweili
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TPermission {

    /**
     * 权限id 主键自增
     */
    private Integer id;

    /**
     * 权限的名称
     */
    private String name;

    /**
     * 权限的关键字
     */
    private String keyword;

    /**
     * 权限描述
     */
    private String description;
}
