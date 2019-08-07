package com.ibond.dcm.daoExtend;

import com.ibond.dcm.dao.DCMBookScheduleDao;
import com.ibond.dcm.entity.DCMBookSchedule;

import java.util.List;
import java.util.Map;

public interface DBDCMBookScheduleDao extends DCMBookScheduleDao {
    List<DCMBookSchedule> selectByParams(Map<String, Object> params);

    DCMBookSchedule selectByBondName(String name);
}