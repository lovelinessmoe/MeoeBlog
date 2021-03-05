package com.meow.blog.service;

import com.meow.blog.entity.Article;

import java.util.List;

public interface ArticleServer {

    List<Article> getPage();

    Integer editArticle(Integer oid, String title, String content, Integer key);

    Article getArticleByOid(Integer oid);
}
