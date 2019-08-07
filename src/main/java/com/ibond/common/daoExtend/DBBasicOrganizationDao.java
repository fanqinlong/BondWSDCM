package com.ibond.common.daoExtend;

import com.ibond.common.dao.BasicOrganizationDao;
import com.ibond.dcm.entity.Organization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DBBasicOrganizationDao extends BasicOrganizationDao {
    List<Organization> listOrganization(@Param("term") String term);
}
