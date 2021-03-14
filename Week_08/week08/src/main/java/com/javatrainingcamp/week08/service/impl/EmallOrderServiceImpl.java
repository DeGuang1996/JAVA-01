package com.javatrainingcamp.week08.service.impl;

import com.javatrainingcamp.week08.domain.EmallOrder;
import com.javatrainingcamp.week08.mapper.EmallOrderMapper;
import com.javatrainingcamp.week08.service.EmallOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author deguang
 * @date 2021/03/07
 */


@Service
public class EmallOrderServiceImpl implements EmallOrderService{

    @Resource
    private EmallOrderMapper emallOrderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Long id) {
        return emallOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(EmallOrder record) {
        return emallOrderMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrUpdate(EmallOrder record) {
        return emallOrderMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(EmallOrder record) {
        return emallOrderMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(EmallOrder record) {
        return emallOrderMapper.insertSelective(record);
    }

    @Override
    public EmallOrder selectByPrimaryKey(Long id) {
        return emallOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(EmallOrder record) {
        return emallOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(EmallOrder record) {
        return emallOrderMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<EmallOrder> list) {
        return emallOrderMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<EmallOrder> list) {
        return emallOrderMapper.batchInsert(list);
    }

}
