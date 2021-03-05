package com.meow.blog.dao;

import com.meow.blog.entity.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer articleid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer articleid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}