package com.ibond.dcm.service;


import com.ibond.common.result.Paging;
import com.ibond.common.result.Result;
import com.ibond.dcm.entity.DCMBookSchedule;

import java.util.List;

public interface IDCMBookScheduleService {

    Result<DCMBookSchedule> addBookSchedule(DCMBookSchedule dcmBookSchedule);

    Result<DCMBookSchedule> updateBookSchedule(DCMBookSchedule dcmBookSchedule);

    Result<DCMBookSchedule> deleteBookSchedule(Long id);

    Result<DCMBookSchedule> listBookScheduleById(Long id);

    Result<Paging<List<DCMBookSchedule>>> listBookSchedule(int page, int rows, String keywords, String outstandingDay);
}
