package com.meow.blog.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meow.blog.dao.ArticleDao;
import com.meow.blog.entity.Article;

import com.meow.blog.service.ArticleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServerImpl implements ArticleServer {
    @Autowired
    private ArticleDao articleDao;


    /**
     * page分页（暂时废弃）
     * @param pageNam
     * @return
     */
    public PageInfo<Article> getPage(Integer pageNam) {

        //PageHelp设置分页
        PageHelper.startPage(pageNam, 10);
        //查询所有文章
        List<Article> articles = articleDao.selectAllArticle();
        //创建pageInfo对象
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        return pageInfo;
    }

    /**
     * get所有文章
     * @return
     */
    @Override
    public List<Article> getPage() {
        return articleDao.selectAllArticle();
    }

    /**
     * 编辑文章方法
     * @param oid
     * @param title
     * @param content
     * @param key
     * @return
     */
    @Override
    public Integer editArticle(Integer oid, String title, String content, Integer key) {
        //TODO
        return null;
    }

    @Override
    public Article getArticleByOid(Integer oid) {
        return articleDao.selectByPrimaryKey(oid);
    }


    @Override
    public Integer updateArticle(Article article) {
        return articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    public Integer addArticle(Article article) {
        return articleDao.insertSelective(article);
    }
}
