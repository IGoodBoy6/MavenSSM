package com.itheima.service.impl;

import com.itheima.dao.TPermissionDao;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.TPermission;
import com.itheima.pojo.TRole;
import com.itheima.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link com.itheima.pojo.TPermission}Service
 *
 * @author wangweili
 */
@Service("permissionService")
public class TPermissionServiceImpl implements BaseService<TPermission, PageBean<TPermission>> {

    /**
     * 自动装配{@link TPermissionDao}的{@link com.github.abel533.mapper.Mapper}代理对象
     */
    @Autowired
    private TPermissionDao tPermissionDao;

    @Override
    public PageBean<TPermission> selectAll(String pageNum,String pageSize,String searchName) {
        return null;
    }

    @Override
    public Integer selectCount(String searchName) {
        return null;
    }

    @Override
    public TPermission selectOne() {
        return null;
    }

    @Override
    public TPermission selectOneWithAdmin(String username,String password) {
        return null;
    }

    @Override
    public List<TRole> selectRoles() {
        return null;
    }

    @Override
    public void handleDeleted(String id) {

    }

    @Override
    public void handleUpdate(String id, String username, String password, String email, String[] roleIds) {

    }

    @Override
    public void handleCreateConfirm(String username, String password, String email, String remark, String[] roleIds) {

    }
}
