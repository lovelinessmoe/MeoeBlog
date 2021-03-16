package com.meow.blog.controller;

import com.meow.blog.entity.Article;
import com.meow.blog.serviceImpl.ArticleServerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author loveliness
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleServerImpl articleServer;

    @Resource
    private HttpSession session;

    /**
     * 获取所有文章基本信息
     *
     * @return article.html
     */
    @RequestMapping("")
    public String getPage() {
        List<Article> list = articleServer.getPage();
        session.setAttribute("list", list);
        System.out.println("list:" + list);
        return "article";
    }


    /**
     * 搜索
     *
     * @param key 密码
     * @return article.html
     */
    @RequestMapping("/{key}")
    public String search(@PathVariable("key") String key) {
        List<Article> search = articleServer.search(key);
        session.setAttribute("list", search);
        System.out.println("search:" + search);
        return "article";
    }

    /**
     * 配置文件中的密码
     */
    @Value("${key}")
    private String key;

    /**
     * 添加或更新文章
     *
     * @param article bean_entity
     * @param keyVal 密码
     * @param content 文章
     */
    @RequestMapping("/addOrUpdateArticle")
    @ResponseBody

    public void edit(Article article, @RequestParam("key") String keyVal, @RequestParam("content") String content) {
        article.setArticletime(new java.sql.Date(System.currentTimeMillis()));


        if (key.equals(keyVal)) {

            article.setArticlecontent(content);

            if (article.getArticleid() != null) {
                if (articleServer.updateArticle(article) > 0) {
                    session.setAttribute("msg", "更新成功");
                }
            } else {
                if (articleServer.addArticle(article) > 0) {
                    session.setAttribute("msg", "添加成功");
                }
            }
        } else {
            session.setAttribute("msg", "密码错误");
        }
        System.out.println("addOrUpdateArticle:" + article);
        System.out.println("keyval" + keyVal);

    }


    /**
     * 通过Oid获取文章内容
     *
     * @param oid 文章id
     * @return 详细文章地址
     */

    @RequestMapping("/getArticleByOid")
    @ResponseBody
    public String getArticleByOid(@RequestParam("oid") String oid) {
        int articleId = Integer.parseInt(oid);
        Article article = articleServer.getArticleByOid(articleId);
        session.setAttribute("article", article);
        System.out.println(article);
        return "/article-detail/" + oid;
    }

    /**
     * 获得所有文章数量
     *
     * @return 文章数量
     */
    @RequestMapping("/getAllCount")
    @ResponseBody
    public Integer getAllCount() {
        List<Article> page = articleServer.getPage();
        return page.size();
    }

}
