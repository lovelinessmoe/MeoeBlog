package com.meow.blog.dao;

import com.meow.blog.entity.ArticleSort;

public interface ArticleSortDao {
    int deleteByPrimaryKey(Integer articleid);

    int insert(ArticleSort record);

    int insertSelective(ArticleSort record);

    ArticleSort selectByPrimaryKey(Integer articleid);

    int updateByPrimaryKeySelective(ArticleSort record);

    int updateByPrimaryKey(ArticleSort record);
}