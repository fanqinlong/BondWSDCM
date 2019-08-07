package com.ibond.common.dao;


import com.ibond.dcm.entity.User;

public interface UserDao {
    User selectByPrimaryKey(Long id);
}