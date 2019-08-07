package com.ibond.common.serviceImpl;

import com.ibond.common.dao.SequenceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * 唯一序号服务
 *
 * @author xiong
 */
@Service
public class SequenceService {

    private static final Logger log = LoggerFactory.getLogger(SequenceService.class);

    @Resource
    private SequenceDao sequenceDao;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long NextID(String tableName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("TABLENAME", tableName);
        sequenceDao.NextID(map);
        Object ob = map.get("IDNO");
        log.info("当前表名：" + tableName + ",返回的sequenceId为" + ob.toString());
        // 系统ID采用的是Long类型，直接转换成Long
        return Long.parseLong(ob.toString());
    }

}
