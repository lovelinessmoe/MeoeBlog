package com.meow.blog.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * article
 * @author 
 */
@Data
public class Article implements Serializable {
    /**
     * 日志自增Id
     */
    private Integer articleid;

    /**
     * 文章名称
     */
    private String articletitle;

    /**
     * 发布时间
     */
    private Date articletime;

    /**
     * 文章内容
     */
    private String articlecontent;

    /**
     * 栏目Id,外键
     */
    private Integer typeid;

    private static final long serialVersionUID = 1L;
}