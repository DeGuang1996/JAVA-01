package com.javatrainingcamp.week07.mapper;

import com.javatrainingcamp.week07.domain.EmallOrder;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author deguang
 * @date 2021/03/07
 */


@Mapper
public interface EmallOrderMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(EmallOrder record);

    int insertOrUpdate(EmallOrder record);

    int insertOrUpdateSelective(EmallOrder record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(EmallOrder record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    EmallOrder selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(EmallOrder record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(EmallOrder record);

    int updateBatch(List<EmallOrder> list);

    int batchInsert(@Param("list") List<EmallOrder> list);
}