package com.ibond.dcm.dao;

import com.ibond.dcm.entity.DCMBookSchedule;

public interface DCMBookScheduleDao {
    int deleteByPrimaryKey(Long id);

    int insert(DCMBookSchedule record);

    int insertSelective(DCMBookSchedule record);

    DCMBookSchedule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DCMBookSchedule record);

    int updateByPrimaryKey(DCMBookSchedule record);
}