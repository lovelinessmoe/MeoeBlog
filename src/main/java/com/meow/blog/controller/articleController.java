package com.meow.blog.controller;

import com.meow.blog.entity.Article;
import com.meow.blog.serviceImpl.ArticleServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/article")
public class articleController {

    @Autowired
    private ArticleServerImpl articleServer;

    @Autowired
    private HttpSession session;

    @RequestMapping("")
    public String getPage(@RequestParam(name = "key", defaultValue = " ") String key) {
        /*
        获取文章基本信息
         */
        System.out.println(key);
        if (!key.equals(" ")) {

            //TODO

        } else {
            List<Article> list = articleServer.getPage();
            session.setAttribute("list", list);
            System.out.println("list" + list);
        }

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
        return "article";
    }

    @RequestMapping("/addOrUpdateArticle")
    public String edit(@RequestParam(name = "oid",defaultValue = " ") String oid ,
                       @RequestParam("title") String title,
                       @RequestParam("content") String content,
                       @RequestParam("key") String keyval
    ) {
        //TODO
        Integer articleid = Integer.parseInt(oid);
        Integer key = Integer.parseInt(keyval);
        System.out.println("article"+newArticle(articleid, title, content, key));

        return "article";
    }


    @RequestMapping("/Edit/{oid}")
    @ResponseBody
    public Article articleEdit(@PathVariable("oid") String oid) {

        int articleid = Integer.parseInt(oid);

        Article article = articleServer.getArticleByOid(articleid);

        session.setAttribute("article", article);

        System.out.println(article);

        return article;
//        return "articleEdit";

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
     * @param key
     * @return
     */
    /*
    @RequestMapping("/getArticleByOid")
    public String getArticleByOid(@RequestParam("oid") String oid){
        int ArticleId = Integer.parseInt(oid);
        Article article = articleServer.getArticleByOid(ArticleId);
        session.setAttribute("article", article);
        System.out.println(article);
        return "/article-detail/"+oid;
    }
*/


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
    @GetMapping("/editArticle")

    public Integer newArticle(@RequestParam("oid") Integer oid,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("key") Integer key) {
        return articleServer.editArticle(oid, title, content, key);

    }


}
