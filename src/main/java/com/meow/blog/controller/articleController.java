package com.meow.blog.controller;

import com.meow.blog.entity.Article;
import com.meow.blog.serviceImpl.ArticleServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/article")
public class articleController {

    @Autowired
    private ArticleServerImpl articleServer;

    @Autowired
    private HttpSession session;

    @RequestMapping("")
    public String getPage() {
        /*
        获取文章基本信息
         */
        List<Article> list = articleServer.getPage();
        session.setAttribute("list", list);
        System.out.println("list:" + list);
        return "article";
    }


    /**
     * 搜索
     *
     * @param key
     * @return
     */
    @RequestMapping("/{key}")
    public String search(@PathVariable("key") String key) {
        List<Article> search = articleServer.search(key);
        session.setAttribute("list",search);
        System.out.println("search:"+search);
        return "article";
    }

    @Value("${key}")
    private String key;

    @RequestMapping("/addOrUpdateArticle")
    @ResponseBody

    public void edit(Article article, @RequestParam("key") String keyVal, @RequestParam("content") String content) {
        //TODO
        article.setArticletime(new java.sql.Date(new Date().getTime()));

        if (key.equals(keyVal)) {

            article.setArticlecontent(content);

            if (article.getArticleid() != null) {
                if (articleServer.updateArticle(article) > 0)
                    session.setAttribute("msg", "更新成功");
            } else {
                if (articleServer.addArticle(article) > 0)
                    session.setAttribute("msg", "添加成功");
            }
        } else {
            session.setAttribute("msg", "密码错误");
        }
        System.out.println("addOrUpdateArticle:" + article);
        System.out.println("keyval" + keyVal);

    }


    @RequestMapping("/Edit/{oid}")
//    @ResponseBody
    public String articleEdit(@PathVariable("oid") String oid) {

        int articleid = Integer.parseInt(oid);

        Article article = articleServer.getArticleByOid(articleid);

        session.setAttribute("article", article);

        System.out.println(article);

//        return article;
        return "articleEdit";

    }

    /*

    @RequestMapping("/Edit")
    public String articleEdit(@RequestParam("oid") String oid) {

        int articleid = Integer.parseInt(oid);

        Article article = articleServer.getArticleByOid(articleid);

        session.setAttribute("article", article);

        return "articleEdit";
    }
    */


    /**
     * 通过Oid获取文章内容
     *
     * @param 
     * @return
     */

    @RequestMapping("/getArticleByOid")
    @ResponseBody
    public String getArticleByOid(@RequestParam("oid") String oid) {
        int ArticleId = Integer.parseInt(oid);
        Article article = articleServer.getArticleByOid(ArticleId);
        session.setAttribute("article", article);
        System.out.println(article);
        return "/article-detail/" + oid;
    }


    /**
     * sql中获取所有文章信息
     *
     * @param key
     * @return
     */

    /*
    @RequestMapping("/getPage")
    public String getPage(@RequestParam(name = "key",defaultValue = " ") String key) {
        System.out.println(key);
        if (!key.equals(" ")) {

        //TODO

        } else {
            List<Article> list = articleServer.getPage();
            session.setAttribute("list", list);
            System.out.println("list"+list);
        }
        return "article";
    }
*/


}
