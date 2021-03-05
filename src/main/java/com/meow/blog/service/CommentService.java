package com.meow.blog.service;

import com.meow.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComment(Integer articleId);


    int addComment(Comment comment);
}
