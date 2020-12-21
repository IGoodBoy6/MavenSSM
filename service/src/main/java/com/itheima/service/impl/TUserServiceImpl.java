package com.itheima.service.impl;

import com.itheima.dao.TUserDao;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.TRole;
import com.itheima.pojo.TUser;
import com.itheima.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * {@link com.itheima.pojo.TUser}Service
 *
 * @author wangweili
 */
@Service("userService")
public class TUserServiceImpl implements BaseService<TUser, PageBean<TUser>> {

    /**
     * 自动装配{@link TUserDao}的{@link com.github.abel533.mapper.Mapper}代理对象
     */
    @Autowired
    private TUserDao tUserDao;


    @Override
    public PageBean<TUser> selectAll(String pageNum, String pageSize, String searchName) {
        Logger.getLogger(BaseService.class).info("pageNum=" + pageNum + " pageSize=" + pageSize + " searchName=" + searchName);
        PageBean<TUser> pageBean = new PageBean<>();
        pageBean.setPageSize(Integer.parseInt(pageSize));
        pageBean.setCurPage(Integer.parseInt(pageNum));
        int startIndex = pageBean.getStartIndex();
        List<TUser> users = tUserDao.selectUserLists(startIndex, Integer.parseInt(pageSize), searchName);
        Logger logger = Logger.getLogger(TUser.class);
        logger.info(users);
        pageBean.setList(users);
        pageBean.setCount(this.selectCount(searchName));
        Logger logger2 = Logger.getLogger(TUser.class);
        logger2.info(pageBean.getCount());
        return pageBean;
    }

    @Override
    public Integer selectCount(String searchName) {
        Logger.getLogger(BaseService.class).info("searchName=" + searchName);
        return tUserDao.selectCount(searchName);
    }

    @Override
    public TUser selectOne() {
        return null;
    }

    @Override
    public TUser selectOneWithAdmin(String username, String password) {
        Logger.getLogger(BaseService.class).info("username=" + username + " password=" + password);
        return tUserDao.selectOneWithAdmin(username, password);
    }

    @Override
    public List<TRole> selectRoles() {
        return tUserDao.selectRoles();
    }

    @Override
    public void handleDeleted(String id) {
        Logger.getLogger(BaseService.class).info("id=" + id);
        tUserDao.deleteMiddles(Integer.parseInt(id));
        tUserDao.handleDeleted(Integer.parseInt(id));
        Logger.getLogger(BaseService.class).info("Delete succeed.");
    }

    @Override
    public void handleUpdate(String id, String username, String password, String email, String[] roleIds) {
        if (roleIds == null) {
            roleIds = new String[]{"3"};
        }
        Logger.getLogger(BaseService.class).info("id=" + id + " username=" + username + " password=" + password + " email=" + email + " roleIds=" + Arrays.toString(roleIds));
        tUserDao.deleteMiddles(Integer.parseInt(id));
        List<String> roles = new ArrayList<>();
        Collections.addAll(roles, roleIds);
        tUserDao.handleUpdate(id, username, password, email);
        for (String role : roles) {
            tUserDao.insertMiddles(Integer.parseInt(id), Integer.parseInt(role));
        }
        Logger.getLogger(BaseService.class).info("Update succeed.");
    }

    @Override
    public void handleCreateConfirm(String username, String password, String email, String remark, String[] roleIds) {
        if (roleIds == null) {
            roleIds = new String[]{"3"};
        }
        Logger.getLogger(BaseService.class).info("username=" + username + " password=" + password + " email=" + email + " remark=" + remark + "roleIds=" + Arrays.toString(roleIds));
        List<String> roles = new ArrayList<>();
        Collections.addAll(roles, roleIds);
        TUser tUser = new TUser();
        tUser.setUsername(username);
        tUser.setPassword(password);
        tUser.setEmail(email);
        tUser.setRemark(remark);
        tUserDao.handleCreateConfirm(tUser);
        for (String role : roles) {
            tUserDao.insertMiddles(tUser.getId(), Integer.parseInt(role));
        }
        Logger.getLogger(BaseService.class).info("Insert succeed.");
    }
}