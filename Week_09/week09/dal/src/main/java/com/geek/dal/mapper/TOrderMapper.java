package com.geek.dal.mapper;

import com.geek.dal.dao.TOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    TOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);

    int updateStatus(TOrder record);
}