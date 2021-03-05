package com.meow.blog.dao;

import com.meow.blog.entity.Comment;

import java.util.List;

public interface CommentDao {
    int deleteByPrimaryKey(Integer commentid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentid);

    List<Comment> selectByArticleId(Integer articleid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}