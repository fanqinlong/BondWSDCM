package com.ibond.common.service;

import com.ibond.common.result.Result;
import com.ibond.dcm.entity.Issuer;
import com.ibond.dcm.entity.Organization;

import java.util.List;

public interface IBasicService {
    Result<List<Organization>> listOrganization(String term);

    Result<List<Issuer>> listIssuer(String term);
}
