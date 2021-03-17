package com.meow.blog.controller;

import com.meow.blog.entity.Article;
import com.meow.blog.entity.Comment;
import com.meow.blog.service.CommentService;
import com.meow.blog.serviceImpl.ArticleServerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author loveliness
 */

@Controller
public class IndexController {

    @Resource
    private ArticleServerImpl articleServer;

    @Resource
    private CommentService commentService;

    @RequestMapping("/")
    public String index1() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * get文章
     *
     * @return message.html
     */


    @RequestMapping("/message")
    public String message(Model model) {
        //对文章id为0的获取消息
        List<Comment> commentList = commentService.getComment(0);
        model.addAttribute("commentList", commentList);
        System.out.println("commentList" + commentList);
        return "message";
    }


    /**
     * 通过articleId获取文章
     *
     * @param articleId 文章id
     * @return 详细
     */

    @RequestMapping("/article-detail/{articleId}")
    public String articleDetail(@PathVariable("articleId") Integer articleId, Model model) {
        Article article = articleServer.getArticleByOid(articleId);
        model.addAttribute("article", article);
        System.out.println("article" + article);

//        获取评论
        List<Comment> commentList = commentService.getComment(articleId);
        model.addAttribute("commentList", commentList);
        System.out.println("commentList" + commentList);

        return "article-detail";
    }

    @RequestMapping("/addMessage")
    @ResponseBody
    public String addComment(@RequestParam("content") String content, @RequestParam("articleOid") String articleOid) {
        System.out.println("comment,articleOid" + articleOid);
        int articleId = Integer.parseInt(articleOid);

        commentService.addComment(new Comment(articleId, content));
        return null;
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }


}
