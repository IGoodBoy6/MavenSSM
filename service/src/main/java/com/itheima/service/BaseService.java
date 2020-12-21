package com.itheima.service;

import com.itheima.pojo.TRole;

import java.util.List;

/**
 * Service的抽象类
 *
 * @author wangweili
 */
public interface BaseService<T, E> {

    /**
     * 查找全表
     *
     * @return 全表的pojo对象
     */
    E selectAll(String pageNum, String pageSize, String searchName);

    /**
     * 查询总数
     *
     * @return 查询的总数
     */
    Integer selectCount(String searchName);

    /**
     * 查询一个
     *
     * @return 查询到的数据
     */
    T selectOne();

    /**
     * 查询是否为管理员
     *
     * @return 查询到的数据
     */
    T selectOneWithAdmin(String username, String password);

    /**
     * 检索所有角色
     *
     * @return TRole
     */
    List<TRole> selectRoles();

    /**
     * 删除指定角色
     *
     * @param id 指定id
     */
    void handleDeleted(String id);

    void handleUpdate(String id, String username, String password, String email, String[] roleIds);

    void handleCreateConfirm(String username, String password, String email, String remark, String[] roleIds);
}
