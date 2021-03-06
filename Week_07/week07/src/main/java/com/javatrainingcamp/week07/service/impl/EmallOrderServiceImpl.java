package com.javatrainingcamp.week07.service.impl;

import com.javatrainingcamp.week07.datasource.DataSourceType;
import com.javatrainingcamp.week07.datasource.MyDataSource;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.javatrainingcamp.week07.domain.EmallOrder;
import com.javatrainingcamp.week07.mapper.EmallOrderMapper;
import com.javatrainingcamp.week07.service.EmallOrderService;
/**
 * @author deguang
 * @date 2021/03/07
 */


@Service
public class EmallOrderServiceImpl implements EmallOrderService{

    @Resource
    private EmallOrderMapper emallOrderMapper;

    @Override
    @MyDataSource(DataSourceType.Master)
    public int deleteByPrimaryKey(Long id) {
        return emallOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    @MyDataSource(DataSourceType.Master)
    public int insert(EmallOrder record) {
        return emallOrderMapper.insert(record);
    }

    @Override
    @MyDataSource(DataSourceType.Master)
    public int insertOrUpdate(EmallOrder record) {
        return emallOrderMapper.insertOrUpdate(record);
    }

    @Override
    @MyDataSource(DataSourceType.Master)
    public int insertOrUpdateSelective(EmallOrder record) {
        return emallOrderMapper.insertOrUpdateSelective(record);
    }

    @Override
    @MyDataSource(DataSourceType.Master)
    public int insertSelective(EmallOrder record) {
        return emallOrderMapper.insertSelective(record);
    }

    @Override
    @MyDataSource(DataSourceType.Slave)
    public EmallOrder selectByPrimaryKey(Long id) {
        return emallOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    @MyDataSource(DataSourceType.Master)
    public int updateByPrimaryKeySelective(EmallOrder record) {
        return emallOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @MyDataSource(DataSourceType.Master)
    public int updateByPrimaryKey(EmallOrder record) {
        return emallOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    @MyDataSource(DataSourceType.Master)
    public int updateBatch(List<EmallOrder> list) {
        return emallOrderMapper.updateBatch(list);
    }

    @Override
    @MyDataSource(DataSourceType.Master)
    public int batchInsert(List<EmallOrder> list) {
        return emallOrderMapper.batchInsert(list);
    }

}
