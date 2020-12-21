package com.itheima.pojo;

import java.util.List;

import lombok.*;

/**
 * 分页Bean
 *
 * @param <T> {@link com.itheima.pojo}类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {

    /**
     * 定义成员变量保存页码上的分页数据
     */
    private List<T> list;

    /**
     * 定义变量保存当前页码
     */
    private int curPage;

    /**
     * 定义成员变量保存每页显示条数
     */
    private int pageSize;

    /**
     * 定义成员变量保存总记录数
     */
    private int count;

    /**
     * 定义成员方法计算起始索引
     *
     * @return 起始索引
     */
    public int getStartIndex() {
        return (curPage - 1) * pageSize;
    }
}
