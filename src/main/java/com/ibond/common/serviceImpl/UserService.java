package com.ibond.common.serviceImpl;

import com.ibond.common.dao.UserDao;
import com.ibond.common.service.IUserService;
import com.ibond.dcm.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    public User selectByPrimaryId(Long id) {
        return userDao.selectByPrimaryKey(id);
    }
}
