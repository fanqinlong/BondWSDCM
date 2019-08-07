package com.ibond.common.dao;

import java.util.Map;

public interface SequenceDao {
	//获取表的序列id【存储过程】
	Map<String, String> NextID(Map<String, String> map);
}
