package com.ibond.common.dao;


import com.ibond.dcm.entity.Issuer;

public interface BasicIssuerDao {
    Issuer selectByPrimaryKey(Long id);
}
