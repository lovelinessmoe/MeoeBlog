package com.meow.blog.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * comment
 * @author 
 */
@Data
public class Comment implements Serializable {
    private Integer commentid;

    private Integer articleid;

    private String content;

    private String username;

    private Date creattime;

    private static final long serialVersionUID = 1L;

    public Comment(Integer articleid, String content) {
        this.articleid = articleid;
        this.content = content;
        this.creattime =new java.util.Date();
//        this.creattime =new java.sql.Date(new java.util.Date().getTime());

    }
}