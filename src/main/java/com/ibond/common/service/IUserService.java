package com.ibond.common.service;


import com.ibond.dcm.entity.User;

public interface IUserService {
    User selectByPrimaryId(Long id);
}
