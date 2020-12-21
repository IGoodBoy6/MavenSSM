package com.itheima.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 用户的pojo类
 *
 * @author wangweili
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUser implements Serializable {

    /**
     * 用户的id 主键自增
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 备注
     */
    private String remark;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updatedTime;

    /**
     * 多表查询的{@link TRole}列表
     */
    private List<TRole> roles;

    /**
     * 接受角色数组
     */
    private String[] roleIds;
}