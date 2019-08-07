package com.ibond.dcm.web;

import com.ibond.common.result.Result;
import com.ibond.common.service.IBasicService;
import com.ibond.dcm.entity.Issuer;
import com.ibond.dcm.entity.Organization;
import com.ibond.dcm.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "dcm/basic")
@Api(tags = "基础数据管理")
public class BasicController {

    @Resource
    private IBasicService basicService;

    @RequestMapping(value = "/org/list", method = RequestMethod.GET)
    @ApiOperation(value = "获取机构列表", notes = "测试数据")
    @ApiImplicitParam(name = "term", value = "查询条件", dataType = "String")
    public Result<List<Organization>> selectOrgs(@ApiIgnore HttpServletRequest request, String term) {

//        List<Organization> list = new ArrayList<>();
//
//        Organization org1 = new Organization();
//        org1.setId(21l);
//        org1.setName("安信信托");
//        org1.setFullName("安信信托股份有限公司");
//
//        Organization org2 = new Organization();
//        org2.setId(22l);
//        org2.setName("河南遂平农商银行");
//        org2.setFullName("河南遂平农村商业银行股份有限公司");
//
//        Organization org3 = new Organization();
//        org3.setId(23l);
//        org3.setName("兴业全球基金公司");
//        org3.setFullName("兴业全球基金管理有限公司");
//
//        list.add(org1);
//        list.add(org2);
//        list.add(org3);
//
//        Result result = new Result(1, "查询成功", list);
        return basicService.listOrganization(term);
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户列表", notes = "测试数据")
    public Result<List<User>> selectUsers() {

        List<User> list = new ArrayList<User>();

        User user1 = new User();
        user1.setId(1l);
        user1.setName("张三");

        User user2 = new User();
        user2.setId(2l);
        user2.setName("李四");

        User user3 = new User();
        user3.setId(3l);
        user3.setName("王五");

        list.add(user1);
        list.add(user2);
        list.add(user3);
        return Result.<List<User>>builder().code(1).msg("success").data(list).build();
    }

    @RequestMapping(value = "/issuer/list", method = RequestMethod.GET)
    @ApiOperation(value = "获取发行人列表", notes = "测试数据")
    @ApiImplicitParam(name = "term", value = "查询条件", dataType = "String")
    public Result<List<Issuer>> selectissuers(String term) {
//        List<Issuer> list = new ArrayList<Issuer>();
//
//        Issuer user1 = new Issuer();
//        user1.setId("024CB36144");
//        user1.setName("云南省交通投资建设集团有限公司");
//        user1.setDate(new Date());
//
//        Issuer user2 = new Issuer();
//        user2.setId("0415116DB8");
//        user2.setName("中国人寿保险股份有限公司");
//        user2.setDate(new Date());
//
//        Issuer user3 = new Issuer();
//        user3.setId("0438094B1B");
//        user3.setName("温州银行股份有限公司");
//        user3.setDate(new Date());
//
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);
//
//        Result result = new Result();
//
//        result.setCode(1);
//        result.setData(list);
//        result.setMsg("success");
        
        return basicService.listIssuer(term);
    }

}
