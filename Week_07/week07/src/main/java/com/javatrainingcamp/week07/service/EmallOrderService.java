package com.javatrainingcamp.week07.service;

import java.util.List;

import com.javatrainingcamp.week07.datasource.DataSourceType;
import com.javatrainingcamp.week07.datasource.MyDataSource;
import com.javatrainingcamp.week07.domain.EmallOrder;

/**
 * @author deguang
 * @date 2021/03/07
 */


public interface EmallOrderService {

    int deleteByPrimaryKey(Long id);

    int insert(EmallOrder record);

    int insertOrUpdate(EmallOrder record);

    int insertOrUpdateSelective(EmallOrder record);

    int insertSelective(EmallOrder record);

    EmallOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmallOrder record);

    int updateByPrimaryKey(EmallOrder record);

    int updateBatch(List<EmallOrder> list);

    int batchInsert(List<EmallOrder> list);

}
