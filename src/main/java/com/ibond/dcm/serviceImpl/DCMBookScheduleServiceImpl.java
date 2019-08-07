package com.ibond.dcm.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibond.common.enums.CommonEnum;
import com.ibond.common.exception.BizException;
import com.ibond.common.result.Paging;
import com.ibond.common.result.Result;
import com.ibond.common.serviceImpl.SequenceService;
import com.ibond.dcm.daoExtend.DBDCMBookScheduleDao;
import com.ibond.dcm.entity.DCMBookSchedule;
import com.ibond.dcm.entity.User;
import com.ibond.dcm.service.IDCMBookScheduleService;
import com.ibond.dcm.utils.AnalysisRequest;
import com.ibond.dcm.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DCMBookScheduleServiceImpl implements IDCMBookScheduleService {

    private static final Logger log = LoggerFactory.getLogger(DCMBookScheduleServiceImpl.class);
    private static String TABLE_NAME_DCMBookSchedule = "dcm_book_schedule";

    @Resource
    private DBDCMBookScheduleDao dbdcmBookScheduleDao;
    @Resource
    private AnalysisRequest analysisRequest;
    @Resource
    private SequenceService sequenceService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Result<DCMBookSchedule> addBookSchedule(DCMBookSchedule dcmBookSchedule) {
        Assert.hasText(dcmBookSchedule.getBondName(), "项目名称不能为空!");
        DCMBookSchedule isExist = dbdcmBookScheduleDao.selectByBondName(dcmBookSchedule.getBondName().trim());
        if (isExist != null) {
            throw new BizException(CommonEnum.DCMBookScheduleExist);
        }
        User user = analysisRequest.getUser();
        dcmBookSchedule.setId(sequenceService.NextID(TABLE_NAME_DCMBookSchedule));
        dcmBookSchedule.setDeleted(Constant.Deleted.NO.getValue());
        dcmBookSchedule.setCreatorId(user.getId());
        dcmBookSchedule.setCreatorName(user.getName());
        dcmBookSchedule.setCreated(new Date());
        dcmBookSchedule.setModifierId(user.getId());
        dcmBookSchedule.setModifierName(user.getName());
        dcmBookSchedule.setModified(new Date());
        int count = dbdcmBookScheduleDao.insertSelective(dcmBookSchedule);
        if (count <= 0) {
            throw new BizException(CommonEnum.DCMAddBookScheduleFailure);
        }
        return Result.<DCMBookSchedule>builder().success(CommonEnum.SUCCESS).data(dcmBookSchedule).build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Result<DCMBookSchedule> updateBookSchedule(DCMBookSchedule dcmBookSchedule) {
        Assert.notNull(dcmBookSchedule.getId(), "簿记日程表ID不能为空!");
        DCMBookSchedule bookSchedule = dbdcmBookScheduleDao.selectByPrimaryKey(dcmBookSchedule.getId());
        if (bookSchedule == null) {
            throw new BizException(CommonEnum.DCMBookScheduleNoExist);
        }
        User user = analysisRequest.getUser();
        dcmBookSchedule.setModifierId(user.getId());
        dcmBookSchedule.setModifierName(user.getName());
        dcmBookSchedule.setModified(new Date());
        int count = dbdcmBookScheduleDao.updateByPrimaryKeySelective(dcmBookSchedule);
        if (count <= 0) {
            throw new BizException(CommonEnum.DCMUpdateBookScheduleFailure);
        }
        return Result.<DCMBookSchedule>builder().success(CommonEnum.SUCCESS).data(dcmBookSchedule).build();
    }

    @Override
    public Result<DCMBookSchedule> deleteBookSchedule(Long id) {
        DCMBookSchedule bookSchedule = dbdcmBookScheduleDao.selectByPrimaryKey(id);
        if (bookSchedule == null) {
            throw new BizException(CommonEnum.DCMBookScheduleNoExist);
        }
        bookSchedule.setDeleted(Constant.Deleted.YES.getValue());
        User user = analysisRequest.getUser();
        bookSchedule.setModifierId(user.getId());
        bookSchedule.setModifierName(user.getName());
        bookSchedule.setModified(new Date());
        int count = dbdcmBookScheduleDao.updateByPrimaryKeySelective(bookSchedule);
        if (count <= 0) {
            throw new BizException(CommonEnum.DCMDeleteBookScheduleFailure);
        }
        return Result.<DCMBookSchedule>builder().success(CommonEnum.SUCCESS).data(bookSchedule).build();
    }

    @Override
    public Result<DCMBookSchedule> listBookScheduleById(Long id) {
        DCMBookSchedule bookSchedule = dbdcmBookScheduleDao.selectByPrimaryKey(id);
        return Result.<DCMBookSchedule>builder().success(CommonEnum.SUCCESS).data(bookSchedule).build();
    }

    @Override
    public Result<Paging<List<DCMBookSchedule>>> listBookSchedule(int page, int rows, String keywords, String outstandingDay) {
        Map<String, Object> params = new HashMap<>();
        params.put("keywords", keywords);
        params.put("outstandingDay", outstandingDay);
        PageHelper.startPage(page, rows, true);
        List<DCMBookSchedule> list = dbdcmBookScheduleDao.selectByParams(params);
        PageInfo<DCMBookSchedule> pageInfo = new PageInfo<>(list);
        return Result.<Paging<List<DCMBookSchedule>>>builder().success(CommonEnum.SUCCESS).data(new Paging<>(pageInfo.getTotal(), pageInfo.getList())).build();
    }


}
