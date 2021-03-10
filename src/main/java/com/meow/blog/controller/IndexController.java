package com.meow.blog.controller;

import com.alibaba.fastjson.JSON;
import com.meow.blog.entity.Article;
import com.meow.blog.entity.Comment;
import com.meow.blog.service.CommentService;
import com.meow.blog.serviceImpl.ArticleServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ArticleServerImpl articleServer;

    @Autowired
    private HttpSession session;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/")
    public String Null() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * get文章
     *
     * @param
     * @return
     */


    @RequestMapping("/message")
    public String message() {
        //对文章id为0的获取消息
        List<Comment> commentList = commentService.getComment(0);
        session.setAttribute("commentList", commentList);
        System.out.println("commentList" + commentList);
        return "message";
    }


    /**
     * 通过articleId获取文章
     *
     * @param ArticleId
     * @return
     */

    @RequestMapping("/article-detail/{articleId}")
    public String article_detail(@PathVariable("articleId") Integer ArticleId) {
        Article article = articleServer.getArticleByOid(ArticleId);
        session.setAttribute("article", article);
        System.out.println("article" + article);

//        获取评论
        List<Comment> commentList = commentService.getComment(ArticleId);
        session.setAttribute("commentList", commentList);
        System.out.println("commentList" + commentList);

        return "article-detail";
    }

    @RequestMapping("/addMessage")
    @ResponseBody
    public String addComment(@RequestParam("content") String content, @RequestParam("articleOid") String articleOid) {
        System.out.println("comment,articleOid" + articleOid);
        int articleid = Integer.parseInt(articleOid);

        int i = commentService.addComment(new Comment(articleid, content));
        return null;
//        return "article-detail";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }


}
