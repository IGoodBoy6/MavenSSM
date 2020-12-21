package com.itheima.dao;


import com.itheima.pojo.TRole;
import com.itheima.pojo.TUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * {@link com.itheima.pojo.TUser}表的Dao接口
 *
 * @author wangweili
 */
public interface TUserDao {

    /**
     * 查询用户密码是否正确及是否为管理员
     *
     * @param username 用户名
     * @param password 密码
     * @return TUser
     */
    TUser selectOneWithAdmin(@Param("username") String username, @Param("password") String password);

    /**
     * 查询所有userList
     *
     * @param pageSize   页面大小
     * @param searchName 模糊搜索名
     * @param startIndex 起始索引
     * @return 查询到的结果
     */
    List<TUser> selectUserLists(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize, @Param("searchName") String searchName);

    /**
     * 查询总数
     *
     * @param searchName 搜索名
     * @return int
     */
    int selectCount(@Param("searchName") String searchName);

    @Select("select * from t_role")
    List<TRole> selectRoles();

    @Delete("delete from t_user where id=#{id}")
    void handleDeleted(int id);

    @Delete("delete from t_user_role where user_id=#{id}")
    void deleteMiddles(int id);

    @Update("update t_user set username=#{username},password=#{password},email=#{email},updateTime=now() where id=#{id}")
    void handleUpdate(@Param("id") String id, @Param("username") String username, @Param("password") String password, @Param("email") String email);

    @Insert("insert into t_user_role values (#{userId},#{roleId})")
    void insertMiddles(@Param("userId") int userId, @Param("roleId") int roleId);

    @Insert("insert into t_user values (null,#{username},#{password},#{remark},#{email},now(),now())")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void handleCreateConfirm(TUser user);
}
