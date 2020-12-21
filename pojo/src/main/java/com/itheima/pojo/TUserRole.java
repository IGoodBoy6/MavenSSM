package com.itheima.pojo;

import lombok.*;

/**
 * {@link TUser}&{@link TRole}中间表
 *
 * @author wangweili
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUserRole {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户id所对应的权限
     */
    private Integer roleId;
}
