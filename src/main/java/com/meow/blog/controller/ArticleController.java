package com.meow.blog.controller;

import com.meow.blog.entity.Article;
import com.meow.blog.serviceImpl.ArticleServerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author loveliness
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleServerImpl articleServer;


    /**
     * 获取所有文章基本信息
     *
     * @return article.html
     */
    @RequestMapping("")
    public String getPage(Model model) {
        List<Article> list = articleServer.getPage();
        model.addAttribute("list", list);
        System.out.println("model");
        System.out.println("list:" + list);
        return "article";
    }


    /**
     * 搜索
     *
     * @param key 关键字
     * @return article.html
     */
    @RequestMapping("/{key}")
    public String search(@PathVariable("key") String key, Model model) {
        List<Article> search = articleServer.search(key);
        model.addAttribute("list", search);
        System.out.println("search:" + search);
        return "article";
    }

    /**
     * Edit
     *
     * @param oid 用户id
     * @return 编辑界面
     */
    @RequestMapping("/Edit/{oid}")
    public String articleEdit(@PathVariable(value = "oid") String oid, Model model) {

        String articleVal = "article";

        if (!articleVal.equals(oid)) {
//            是数字
            int articleId = Integer.parseInt(oid);
            Article article = articleServer.getArticleByOid(articleId);
            model.addAttribute("article", article);
            System.out.println(article);
        } else {
            model.addAttribute("msg", null);
            model.addAttribute("article", new Article());
        }

        return "articleEdit";

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
     * @param key     密码
     * @param content 文章
     */
    @RequestMapping("/addOrUpdateArticle")
    @ResponseBody

    public void edit(Article article, String key, String content, Model model) {
        article.setArticletime(new java.sql.Date(System.currentTimeMillis()));


        if (this.key.equals(key)) {
            article.setArticlecontent(content);

            if (article.getArticleid() != null) {
                if (articleServer.updateArticle(article) > 0) {
                    model.addAttribute("msg", "更新成功");
                }
            } else {
                if (articleServer.addArticle(article) > 0) {
                    model.addAttribute("msg", "添加成功");
                }
            }
        } else {
            model.addAttribute("msg", "密码错误");

        }
        System.out.println("addOrUpdateArticle:" + article);
        System.out.println("keyval" + key);

    }


    /**
     * 通过Oid获取文章内容
     *
     * @param oid 文章id
     * @return 详细文章地址
     */

    @RequestMapping("/getArticleByOid")
    @ResponseBody
    public String getArticleByOid(@RequestParam("oid") String oid, Model model) {
        int articleId = Integer.parseInt(oid);
        Article article = articleServer.getArticleByOid(articleId);
        System.out.println(article);
        model.addAttribute("article", article);
        return "article-detail/" + oid;
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
