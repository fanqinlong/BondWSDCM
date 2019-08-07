package com.ibond.common.dao;


import com.ibond.dcm.entity.Organization;

public interface BasicOrganizationDao {
    Organization selectByPrimaryKey(Long id);
}
