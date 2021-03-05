package com.meow.blog.serviceImpl;

import com.meow.blog.dao.CommentDao;
import com.meow.blog.entity.Comment;
import com.meow.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> getComment(Integer articleId) {
        return commentDao.selectByArticleId(articleId);
    }

    @Override
    public int addComment(Comment comment) {
        return commentDao.insertSelective(comment);
    }
}
