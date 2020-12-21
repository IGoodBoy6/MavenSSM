package com.itheima.controller;

import com.itheima.pojo.PageBean;
import com.itheima.pojo.TPermission;
import com.itheima.pojo.TRole;
import com.itheima.pojo.TUser;
import com.itheima.service.BaseService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * {@link Controller}
 *
 * @author wangweili
 */
@Controller
@RequestMapping("/user")
public class WebController {
    /**
     * 自动装配的{@link com.itheima.service.impl.TPermissionServiceImpl}
     */
    @Autowired
    private BaseService<TPermission, PageBean<TPermission>> permissionService;

    /**
     * 自动装配的{@link com.itheima.service.impl.TUserServiceImpl}
     */
    @Autowired
    private BaseService<TUser, PageBean<TUser>> userService;

    /**
     * 登录校验
     */
    @RequestMapping("/login")
    @SneakyThrows
    public String login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        TUser tUser = userService.selectOneWithAdmin(username, password);
        if (tUser == null) {
            return "../login.jsp";
        }
        request.getSession().setAttribute("LOGIN_USER", tUser);
        return "redirect:../index.html";
    }

    /**
     * 登录扫描角色
     *
     * @return 显示在主界面的list列表
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @SneakyThrows
    @ResponseBody
    public PageBean<TUser> getUserList(String pageNum, String pageSize, String searchName) {
        return userService.selectAll(pageNum, pageSize, searchName);
    }

    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    @SneakyThrows
    @ResponseBody
    public List<TRole> getRoleList() {
        return userService.selectRoles();
    }

    @RequestMapping(value = "/handleDeleted", method = RequestMethod.GET)
    @SneakyThrows
    @ResponseBody
    public PageBean<TUser> handleDeleted(String id, String pageNum, String pageSize, String searchName) {
        userService.handleDeleted(id);
        return this.getUserList(pageNum, pageSize, searchName);
    }

    @RequestMapping(value = "/handleUpdate", method = RequestMethod.GET)
    @SneakyThrows
    @ResponseBody
    public PageBean<TUser> handleUpdate(String id, String username, String password, String email, String pageNum, String pageSize, String searchName, HttpServletRequest request) {
        String[] roleIds = request.getParameterValues("roleIds[]");
        userService.handleUpdate(id, username, password, email, roleIds);
        return this.getUserList(pageNum, pageSize, searchName);
    }

    @RequestMapping(value = "/handleCreateConfirm", method = RequestMethod.GET)
    @SneakyThrows
    @ResponseBody
    public PageBean<TUser> handleCreateConfirm(String username, String password, String email, String pageNum, String remark, String pageSize, String searchName, HttpServletRequest request) {
        String[] roleIds = request.getParameterValues("roleIds[]");
        userService.handleCreateConfirm(username, password, email, remark, roleIds);
        return this.getUserList(pageNum, pageSize, searchName);
    }
}