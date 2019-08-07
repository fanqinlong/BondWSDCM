package com.ibond.common.serviceImpl;

import com.ibond.common.daoExtend.DBBasicIssuerDao;
import com.ibond.common.daoExtend.DBBasicOrganizationDao;
import com.ibond.common.service.IBasicService;
import com.ibond.dcm.entity.Issuer;
import com.ibond.dcm.entity.Organization;
import com.ibond.common.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BasicServiceImpl implements IBasicService {

    @Resource
    private DBBasicOrganizationDao dbBasicOrganizationDao;

    @Resource
    private DBBasicIssuerDao dbBasicIssuerDao;


    @Override
    public Result<List<Organization>> listOrganization(String term) {
        List<Organization> list = dbBasicOrganizationDao.listOrganization(term);
        return Result.<List<Organization>>builder().code(1).msg("查询成功").data(list).build();
    }

    @Override
    public Result<List<Issuer>> listIssuer(String term) {
        List<Issuer> list = dbBasicIssuerDao.listIssuer(term);
        return Result.<List<Issuer>>builder().code(1).msg("查询成功").data(list).build();
    }
}
