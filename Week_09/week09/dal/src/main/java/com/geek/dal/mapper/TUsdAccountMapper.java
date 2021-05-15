package com.geek.dal.mapper;

import com.geek.dal.dao.TOrder;
import com.geek.dal.dao.TUsdAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TUsdAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TUsdAccount record);

    int insertSelective(TUsdAccount record);

    TUsdAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TUsdAccount record);

    int updateByPrimaryKey(TUsdAccount record);

    int updateByPrimaryKeyAndOrder(@Param("tUsdAccount") TUsdAccount tUsdAccount, @Param("tOrder") TOrder tOrder);

    TUsdAccount selectAllByUid(String uid);
}