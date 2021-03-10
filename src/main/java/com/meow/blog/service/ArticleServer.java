package com.meow.blog.service;

import com.meow.blog.entity.Article;

import java.util.List;

public interface ArticleServer {

    List<Article> getPage();

    Article getArticleByOid(Integer oid);

    Integer updateArticle(Article article);

    Integer addArticle(Article article);

    List<Article> search(String key);
}
