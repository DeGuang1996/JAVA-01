package com.geek.dal.mapper;

import com.geek.dal.dao.TOrder;
import com.geek.dal.dao.TRmbAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TRmbAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TRmbAccount record);

    int insertSelective(TRmbAccount record);

    TRmbAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TRmbAccount record);

    int updateByPrimaryKey(TRmbAccount record);

    int updateByPrimaryKeyAndOrder(@Param("tRmbAccount") TRmbAccount tRmbAccount, @Param("tOrder") TOrder tOrder);

    TRmbAccount selectAllByUid(String uid);
}