package com.meow.blog.dao;

import com.meow.blog.entity.Article;

import java.util.List;

public interface ArticleDao {
    int deleteByPrimaryKey(Integer articleid);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleid);

    List<Article> selectAllArticle();

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
}