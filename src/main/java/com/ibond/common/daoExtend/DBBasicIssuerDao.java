package com.ibond.common.daoExtend;

import com.ibond.common.dao.BasicIssuerDao;
import com.ibond.dcm.entity.Issuer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DBBasicIssuerDao extends BasicIssuerDao {
    List<Issuer> listIssuer(@Param("term") String term);
}
