package com.ibond.dcm.web;

import com.ibond.common.result.Paging;
import com.ibond.common.result.Result;
import com.ibond.dcm.entity.DCMBookSchedule;
import com.ibond.dcm.service.IDCMBookScheduleService;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "dcm/book")
@Api(tags = "簿记日程管理")
public class DCMBookScheduleController {


    @Resource
    private IDCMBookScheduleService dcmBookScheduleService;

    @RequestMapping(value = "/addBookSchedule", method = RequestMethod.POST)
    @ApiOperation(value = "簿记日程新增操作", notes = "@RequestBody 簿记日程新增接口")
    public Result<DCMBookSchedule> addBookSchedule(@RequestBody @ApiParam(name = "dcmBookSchedule", value = "必填字段:\n bondId\n bondName\n ", required = true) DCMBookSchedule dcmBookSchedule) {
        return dcmBookScheduleService.addBookSchedule(dcmBookSchedule);
    }

    @RequestMapping(value = "/updateBookSchedule", method = RequestMethod.POST)
    @ApiOperation(value = "簿记日程修改操作", notes = "@RequestBody 簿记日程修改接口")
    public Result<DCMBookSchedule> updateBookSchedule(@RequestBody @ApiParam(name = "dcmBookSchedule", value = "必填字段:\n bondId\n bondName\n ", required = true) DCMBookSchedule dcmBookSchedule) {
        return dcmBookScheduleService.updateBookSchedule(dcmBookSchedule);
    }

    @RequestMapping(value = "/deleteBookSchedule", method = RequestMethod.GET)
    @ApiOperation(value = "簿记日程删除操作", notes = "@RequestBody 簿记日程删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "簿记日程表id", required = true, dataType = "Long")
    })
    public Result<DCMBookSchedule> deleteBookSchedule(@NotNull(message = "簿记日程表id不能为空!") @RequestParam Long id) {
        return dcmBookScheduleService.deleteBookSchedule(id);
    }

    @RequestMapping(value = "/listBookScheduleById", method = RequestMethod.GET)
    @ApiOperation(value = "簿记日程根据ID查询操作", notes = "@RequestBody 簿记日程根据ID查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "簿记日程表id", required = true, dataType = "Long")
    })
    public Result<DCMBookSchedule> listBookScheduleById(@NotNull(message = "簿记日程表id不能为空!") @RequestParam Long id) {
        return dcmBookScheduleService.listBookScheduleById(id);
    }


    @RequestMapping(value = "/listOpponentDepository", method = RequestMethod.GET)
    @ApiOperation(value = "簿记日程分页查询操作", notes = "根据参数，簿记日程分页查询操接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前查询页码, 从1开始取值", required = true, dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每次查询条数, 取值大于0", required = true, dataType = "int"),
            @ApiImplicitParam(name = "keywords", value = "输入查询的关键字, 任意值", dataType = "String"),
            @ApiImplicitParam(name = "outstandingDay", value = "上市日, 任意值", dataType = "String")
    })
    public Result<Paging<List<DCMBookSchedule>>> listBookSchedule(@Min(value = 1, message = "当前查询页码, 从1开始取值!") @RequestParam int page, @Min(value = 0, message = "每次查询条数, 取值大于0!") @RequestParam int rows, String keywords, String outstandingDay) {
        return dcmBookScheduleService.listBookSchedule(page, rows, keywords, outstandingDay);
    }


}
